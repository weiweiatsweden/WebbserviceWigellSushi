package com.wei.wigellsushi.service;

import com.wei.wigellsushi.Logging.Log4j;

import com.wei.wigellsushi.model.*;
import com.wei.wigellsushi.repository.DishTakeAwayRepository;
import com.wei.wigellsushi.repository.MenyRepository;
import com.wei.wigellsushi.repository.TakeAwayRepository;
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
public class TakeAwayService implements TakeAwayServiceInterface{
    @Autowired
    private MenyRepository dishesRepository;

    @Autowired
    private TakeAwayRepository takeAwayRepository;

    @Autowired
    private DishTakeAwayRepository dishTakeAwayRepository;


    @Autowired
    private MenyService menyService;


    @Override
    public TakeAwayOrders placeTakeAwayOrder(TakeAwayOrders takeAwayOrders) {
        BigDecimal totalPriceSEK = calculateTotalPriceSEK(takeAwayOrders);
        takeAwayOrders.setTotalPriceSek(totalPriceSEK);

        try {
            takeAwayOrders.setTotalPriceEuro(calculateTotalPriceEuro(totalPriceSEK));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        takeAwayRepository.save(takeAwayOrders);
        for (DishTakeAway dta : takeAwayOrders.getDishTakeAway()){
            dta.setTakeAwayOrders(takeAwayOrders);
        }
        dishTakeAwayRepository.saveAll(takeAwayOrders.getDishTakeAway());
        Log4j.logger.info("Customer ordered a take-away: " + takeAwayOrders);
        return takeAwayOrders;
    }



    @Override
    public List<TakeAwayOrders> getAllTakeAwayOrders() {
        return takeAwayRepository.findAll();

    }

    private BigDecimal calculateTotalPriceSEK (TakeAwayOrders takeAwayOrders){

        BigDecimal totalPriceSEK = new BigDecimal(0);
        List<Dishes> dishesList = menyService.getAllDishes();
        for (Dishes dish : dishesList) {
            for (DishTakeAway dishTakeAway : takeAwayOrders.getDishTakeAway()) {
                if (dishTakeAway.getDish().getDishID() == dish.getDishID()) {
                    BigDecimal price = dish.getPriceSek().multiply(BigDecimal.valueOf(dishTakeAway.getQuantity()));
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


    }






