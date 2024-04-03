import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Displaying our weather app
                new WeatherAppGui().setVisible(true);
            }
        });
        System.out.println(Weather_fetching.get_weather_data("rourkela"));
    }

}