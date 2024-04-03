import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WeatherAppGui extends JFrame{
    private JSONObject weather_data;
    public WeatherAppGui(){

        setSize(500,600);
        setTitle("Window");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Toolkit toolkit=getToolkit();
        Dimension size=toolkit.getScreenSize();
        setLocation(size.width/2-getWidth()/2,  (size.height/2-getWidth()/2));
        JButton but=new JButton(loadImage("src/images/search.png"));
        but.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        but.setBounds(400,30,45,45);
        add(but);

        //working field initiated
        JTextArea search=new JTextArea();
        // working field boundries
        search.setBounds(30,30,350,45);
        // working field style
        search.setFont(new Font("Dialog",Font.PLAIN,25));

        //added to the jframe container
        add(search);

        //cloudy image implementation
        JLabel weatherImage=new JLabel(loadImage("src/images/cloudy.png"));
        weatherImage.setBounds(0,100,450,217);
        add(weatherImage);

        //Temperature text implementation
        JLabel temperature_text=new JLabel("10 c");
        temperature_text.setBounds(0,200,450,217);
        temperature_text.setFont(new Font("Dialog",Font.PLAIN,48));

        temperature_text.setHorizontalAlignment(SwingUtilities.CENTER);
        add(temperature_text);

        //

        JLabel naming_the_weather=new JLabel("Cloudy");
        naming_the_weather.setBounds(0,330,450,50);
        naming_the_weather.setFont(new Font("Dialog",Font.PLAIN,45));
        naming_the_weather.setHorizontalAlignment(SwingUtilities.CENTER);
        add(naming_the_weather);

        //adding humidaty image
        JLabel humidity_image=new JLabel(loadImage("src/images/humidity.png"));
        humidity_image.setBounds(20,450,85,100);
        add(humidity_image);

        //humidity text
        JLabel humidity_text=new JLabel("<html><b>Humidity</b> 100%</html>");
        humidity_text.setBounds(100,430,85,150);
        humidity_text.setFont(new Font("Dialog",Font.PLAIN,16));
        add(humidity_text);

        JLabel wind_speed_image=new JLabel(loadImage("src/images/windspeed.png"));
        wind_speed_image.setBounds(300,450,85,100);
        add(wind_speed_image);


        //windspeedtext inplement
        JLabel wind_speed_text=new JLabel("<html><b>Windspeed</b> 15km/hr </html>");
        wind_speed_text.setBounds(350,430,85,150);
        wind_speed_text.setFont(new Font("Dialog",Font.PLAIN,16));
        add(wind_speed_text);




        //action implementation

        but.addActionListener(new ActionListener() {
//            @Override
            public void actionPerformed(ActionEvent e) {
                //location from the user
                String userInput=search.getText();

                //validate inpute-removing white space from the input
                if (userInput.replaceAll(" \\s","").length()<=0){
                    return;
                }
                weather_data=Weather_fetching.get_weather_data(userInput);

                //updating the gui with the data

                //updating the image
                String weather_current_condition= (String) weather_data.get("Weather_condition");
                weather_current_condition.toLowerCase();


                switch ((weather_current_condition)){
                    case "clear":
                        weatherImage.setIcon(loadImage("src/images/clear.png"));
                        break;
                    case "cloudy":
                        weatherImage.setIcon(loadImage("src/images/cloudy.png"));
                        break;
                    case "rain":
                        weatherImage.setIcon(loadImage("src/images/rain.png"));
                        break;
                    case "snow":
                        weatherImage.setIcon(loadImage("src/images/snow.png"));
                        break;
                }



                //updating temperature
                double curr_temp= (double) weather_data.get("Temperature");
                temperature_text.setText(curr_temp+" C");


                //updating weather condition text
                naming_the_weather.setText(weather_current_condition);


                //updating windspeed
                double curr_wind_speed= (double) weather_data.get("Windspeed");
                wind_speed_text.setText("<html><b>Windspeed</b>" + curr_wind_speed +" </html>");




                //updating the humididty
                long curr_humi= (long) weather_data.get("Humidity");
                humidity_text.setText("<html><b>Humidity </b>"+curr_humi+"%</html>");

            }
        });

    }

    private ImageIcon loadImage(String image_path){
        try {
            BufferedImage image = ImageIO.read(new File(image_path));
            return new ImageIcon(image);
        }
        catch (IOException e){
            System.out.println("You have entered the wrong path of the image.");
        }
        return null;

    }
}
