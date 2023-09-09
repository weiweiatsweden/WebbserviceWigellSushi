package com.wei.wigellsushi.service;

import com.wei.wigellsushi.model.Bookings;
import com.wei.wigellsushi.model.DishBooking;
import com.wei.wigellsushi.model.Room;
import com.wei.wigellsushi.model.User;
import com.wei.wigellsushi.repository.BookingsRepository;
import com.wei.wigellsushi.repository.RoomRepository;
import com.wei.wigellsushi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TODO log4j
@Service
public class BookingService implements BookingServiceInterface {
    @Autowired
    private BookingsRepository bookingsRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Bookings bookRoom(Bookings booking) {

        booking.setTotalPriceSek(calculateTotalPriceSEK(booking));
        try {
            booking.setTotalPriceEuro(calculateTotalPriceEuro(booking.getTotalPriceSek()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bookingsRepository.save(booking);
        Room room = roomRepository.findById(booking.getRoomID()).get();
        room.setBooked(true);
        roomRepository.save(room);
        


        return booking;
    }


    @Override
    public Bookings updateBooking(Bookings booking, int bookingID) {
        Bookings bookingToUpdate = bookingsRepository.findBookingsByBookingID(bookingID);

        bookingToUpdate.setNumberOfGuests(booking.getNumberOfGuests());
        bookingToUpdate.setRoomID(booking.getRoomID());
        bookingToUpdate.setDishBooking(booking.getDishBooking());
        bookingToUpdate.setActive(booking.getActive());
        bookingToUpdate.setTotalPriceSek(calculateTotalPriceSEK(bookingToUpdate));
        try {
            bookingToUpdate.setTotalPriceEuro(calculateTotalPriceEuro(bookingToUpdate.getTotalPriceSek()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        bookingsRepository.save(bookingToUpdate);

        return bookingToUpdate;
    }

    private BigDecimal calculateTotalPriceSEK(Bookings booking){

        BigDecimal totalPriceSEK = new BigDecimal(0);

        for (DishBooking d : booking.getDishBooking()) {
            BigDecimal sek = d.getDish().getPriceSek();
            BigDecimal qty = BigDecimal.valueOf(d.getQuantity());
            BigDecimal price = sek.multiply(qty);
            totalPriceSEK = totalPriceSEK.add(price);
            //totalPriceSEK.add(d.getDish().getPriceSek().multiply(BigDecimal.valueOf(d.getQuantity())));
        }

        return totalPriceSEK;


    }

    private BigDecimal calculateTotalPriceEuro(BigDecimal sek) throws IOException {
        BigDecimal totalPriceEuro = new BigDecimal(0);

        URL url =  new URL("https://v6.exchangerate-api.com/v6/fd910a7e3d1255ef612fb638/latest/SEK");
        String result = stream(url);

        String[] lines = result.split("\\r?\\n|\\r");
        Pattern p = Pattern.compile("(\\d.+\\d)");
        Matcher matcher;
        String s = "";

        BigDecimal euroCurrency;

        for(int i = 0; i < lines.length; i++) {
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

    public static String stream(URL url) {
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
    public List<Bookings> getAllBookings(int userID)  {
        List<Bookings> getAllBookings = bookingsRepository.findBookingsByUser(userRepository.findById(userID).get());

        return getAllBookings;

    }

    @Override
    public Room updateRoom(Room room, int roomID) {
        return null;
    }

}
