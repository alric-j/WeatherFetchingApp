package com.trial.demo;

import com.codename1.googlemaps.MapContainer;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.trial.demo.com.trial.demo.utils.Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


public class WeatherUI extends Form {
    Form form;
    MapContainer.MapObject marker = null;


    public WeatherUI(Resources theme) {
        form = this;
        getToolbar().setTitle("Weather");
        TableLayout tableLayout = new TableLayout(1, 1);
        setLayout(tableLayout);
        Container text_container = new Container(new TableLayout(1,2));
        TextField city_search = new TextField();
        Button b = new Button();
        FontImage img = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, b.getUnselectedStyle());
        b.setIcon(img);
        text_container.add(new TableLayout.Constraint().heightPercentage(100).widthPercentage(80), city_search);
        text_container.add(b);




        MapContainer map = new MapContainer("AIzaSyCILko2qE79upZnn8msAcifHfvCIeuk9Hc");
        //Container container = new Container();

        Container top_align = new Container(new BorderLayout());
        top_align.add(BorderLayout.NORTH,text_container);
        map.add(top_align);
        Style s = new Style();
        s.setFgColor(0xff0000);
        s.setBgTransparency(0);
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s);
        map.addMarker(EncodedImage.createFromImage(markerImg, false),
                map.getCameraPosition(),
                "Hi marker", "", evt -> {});


        add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(100), map);

        city_search.setDoneListener(evt->{
            String city = city_search.getText();
            try {
                if(marker != null) {
                    map.removeMapObject(marker);
                }
                Map<String,Object> response_coords = ( Map<String,Object>)(new Server().MyGETRequest(city).get("coord"));
                //double latitude = (double)response_coords.get("lat");
                //int longitude = (int)
                Coord coord = new Coord((double)response_coords.get("lat"),(double)response_coords.get("lon"));
                map.setCameraPosition(coord);
                map.zoom(coord,map.getMaxZoom());
                 marker = map.addMarker(EncodedImage.createFromImage(markerImg, false),
                        map.getCameraPosition(),
                        "Hi marker", "", e3 -> {});

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }

        });
        b.addActionListener(e1-> {String city = city_search.getText();
            try {
                if(marker != null) {
                    map.removeMapObject(marker);
                }
                Map<String,Object> response_coords = ( Map<String,Object>)(new Server().MyGETRequest(city).get("coord"));
                //double latitude = (double)response_coords.get("lat");
                //int longitude = (int)
                Coord coord = new Coord((double)response_coords.get("lat"),(double)response_coords.get("lon"));
                map.setCameraPosition(coord);
                map.zoom(coord,map.getMaxZoom());
                map.addMarker(EncodedImage.createFromImage(markerImg, false),
                        map.getCameraPosition(),
                        "Hi marker", "", e3 -> {});

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error");
            }});
        //Button b = new Button("Enter");
        //b.addActionListener(e -> Dialog.show("hey", "hello", "ok", "cancel"));
        //Container bottomContainer = new Container(new BorderLayout());
        //bottomContainer.add(BorderLayout.SOUTH, b);
       // bottomContainer.setUIID("BottomContainer");
       // add(tableLayout.createConstraint().widthPercentage(100).heightPercentage(45), bottomContainer);
        show();
    }


}



