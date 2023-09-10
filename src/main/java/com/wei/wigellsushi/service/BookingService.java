package com.wei.wigellsushi.service;

import com.wei.wigellsushi.Logging.Log4j;
import com.wei.wigellsushi.model.*;
import com.wei.wigellsushi.repository.BookingsRepository;
import com.wei.wigellsushi.repository.DishBookingRepository;
import com.wei.wigellsushi.repository.RoomRepository;
import com.wei.wigellsushi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private DishBookingRepository dishBookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenyService menyService;

    @Override
    public Bookings bookRoom(Bookings booking) {

        BigDecimal totalPriceSEK = calculateTotalPriceSEK(booking);
        booking.setTotalPriceSek(totalPriceSEK);

        try {
            booking.setTotalPriceEuro(calculateTotalPriceEuro(totalPriceSEK));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Room room = roomRepository.findById(booking.getRoomID()).get();

        if (room.getIsBooked()) {
        throw new RuntimeException("This room is already booked");
             } else {
        room.setIsBooked(true);
        bookingsRepository.save(booking);
        for (DishBooking db : booking.getDishBooking()){
            db.setBooking(booking);
        }
        dishBookingRepository.saveAll(booking.getDishBooking());
        roomRepository.save(room);
        Log4j.logger.info("Customer added a booking: " + booking);

        return booking;
        }
    }


        @Override
        public Bookings updateBooking (Bookings booking,int bookingID){
            Bookings bookingToUpdate = bookingsRepository.findBookingsByBookingID(bookingID);

            bookingToUpdate.setNumberOfGuests(booking.getNumberOfGuests());
            bookingToUpdate.setRoomID(booking.getRoomID());

            bookingToUpdate.setActive(booking.getActive());
            BigDecimal totalPriceSEK = calculateTotalPriceSEK(booking);
            bookingToUpdate.setTotalPriceSek(totalPriceSEK);
            try {
                bookingToUpdate.setTotalPriceEuro(calculateTotalPriceEuro(totalPriceSEK));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            dishBookingRepository.deleteAll(bookingToUpdate.getDishBooking());


            bookingToUpdate.setDishBooking(null);
            bookingsRepository.save(bookingToUpdate);

            for (DishBooking db : booking.getDishBooking()){
                db.setBooking(booking);
            }
            dishBookingRepository.saveAll(booking.getDishBooking());

            bookingToUpdate.setDishBooking(booking.getDishBooking());

            Log4j.logger.info("Customer changed a booking: " + booking);
            return bookingToUpdate;
        }

        private BigDecimal calculateTotalPriceSEK (Bookings booking){

            BigDecimal totalPriceSEK = new BigDecimal(0);
            List<Dishes> dishesList = menyService.getAllDishes();
            for (Dishes dish : dishesList) {
                for (DishBooking dishBooking : booking.getDishBooking()) {
                    if (dishBooking.getDish().getDishID() == dish.getDishID()) {
                        BigDecimal price = dish.getPriceSek().multiply(BigDecimal.valueOf(dishBooking.getQuantity()));
                        totalPriceSEK = totalPriceSEK.add(price);

                    }
                }

            }

            return totalPriceSEK;


        }

        private BigDecimal calculateTotalPriceEuro(BigDecimal sek) throws IOException {
            BigDecimal totalPriceEuro = new BigDecimal(0);

            URL url = new URL("https://v6.exchangerate-api.com/v6/fd910a7e3d1255ef612fb638/latest/SEK");
            String result = stream(url);

            String[] lines = result.split("\\r?\\n|\\r");
            Pattern p = Pattern.compile("(\\d.+\\d)");
            Matcher matcher;
            String s = "";

            BigDecimal euroCurrency;

            for (int i = 0; i < lines.length; i++) {
                if (lines[i].contains("EUR")) {
                    matcher = p.matcher(lines[i]);
                    if (matcher.find()) {
                        euroCurrency = new BigDecimal(matcher.group(1));
                        totalPriceEuro = sek.multiply(euroCurrency);
                        break;
                    }
                }
            }

            return totalPriceEuro;
        }

        public static String stream (URL url){
            try (InputStream input = url.openStream()) {
                InputStreamReader isr = new InputStreamReader(input);
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder json = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    json.append((char) c);
                }
                return json.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public List<Bookings> getAllBookings ( int userID){
            List<Bookings> getAllBookings = bookingsRepository.findBookingsByUser(userRepository.findById(userID).get());

            return getAllBookings;

        }



    }

