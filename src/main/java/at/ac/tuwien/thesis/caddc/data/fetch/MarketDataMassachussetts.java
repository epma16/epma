package at.ac.tuwien.thesis.caddc.data.fetch;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import at.ac.tuwien.thesis.caddc.data.parse.HTMLParser;
import at.ac.tuwien.thesis.caddc.data.parse.NordPoolHTMLParser;
import at.ac.tuwien.thesis.caddc.model.Location;
import at.ac.tuwien.thesis.caddc.persistence.ImportDataException;
import at.ac.tuwien.thesis.caddc.rest.client.RESTClient;



/**
 * Defines a MarketData Instance responsible for retrieving energy
 * market data from Massachussetts for different data sources
 */
public class MarketDataMassachussetts extends MarketData {
	

	/**
	 * Create a MarketData Instance with the given location
	 * @param location the location for this MarketData Instance
	 */
	public MarketDataMassachussetts(Location location) {
		super(location);
	}

	/**
	 * Get the energy prices as a list of Strings for the given year
	 * @param year the year for which to obtain energy prices
	 * @return a list of Strings containing the energy price time series
	 * @throws ImportDataException is thrown when the data import failed
	 */
	@Override
	public List<String> getPrices(Integer year) throws ImportDataException {
		String url = "";
    	if(year == 2014) {
    		url = "http://www.iso-ne.com/static-assets/documents/2015/05/2014_smd_hourly.xls";
    	}
    	else {
    		url = "http://www.iso-ne.com/static-assets/documents/markets/hstdata/znl_info/hourly/"+year+"_smd_hourly.xls";
    	}
		int[] colIdx = {0,1,4};
		
		List<String> priceList;
		try {
			priceList = RESTClient.fetchAndParseXLS(url, // fetch URL
																	9, // sheet number
																	1, // row Offset
																	colIdx // column indices array
																);
		} catch (ConnectException e) {
			throw new ImportDataException("ImportDataException: "+e.getLocalizedMessage());
		}
		List<String> prices = new ArrayList<String>();
		for(String price : priceList) {
			String[] split = price.split(";");
			int hour = (int)Double.parseDouble(split[1]); // parse hour
			hour --; // reduce hour by one to get hours from 0-23 instead of 1-24
			String result = split[0] + ";" + String.valueOf(hour) + ";" + split[2];
			prices.add(result);
		}
		return prices;
	}
	
}