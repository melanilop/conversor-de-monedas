import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TasaDivisa {
    private String jsonResponse;

    public TasaDivisa(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    public double obtenerTipoCambio(String monedaObjetivo) {
        // Parsear la respuesta JSON y obtener el tipo de cambio para la moneda objetivo
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        double tipoCambio = conversionRates.get(monedaObjetivo).getAsDouble();
        return tipoCambio;
    }
}
