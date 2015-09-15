package at.ac.tuwien.thesis.caddc.data.market;

import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import at.ac.tuwien.thesis.caddc.data.fetch.DataFetch;
import at.ac.tuwien.thesis.caddc.data.fetch.DataFetcher;
import at.ac.tuwien.thesis.caddc.data.fetch.DataFetcherFinland;
import at.ac.tuwien.thesis.caddc.data.fetch.DataFetcherSweden;
import at.ac.tuwien.thesis.caddc.data.fetch.FetchDataException;
import at.ac.tuwien.thesis.caddc.data.fetch.FileDataFetch;
import at.ac.tuwien.thesis.caddc.data.fetch.URLDataFetch;
import at.ac.tuwien.thesis.caddc.data.parse.Parser;
import at.ac.tuwien.thesis.caddc.data.parse.ParserFinland;
import at.ac.tuwien.thesis.caddc.data.parse.ParserSweden;
import at.ac.tuwien.thesis.caddc.data.parse.types.HTMLTableParser;
import at.ac.tuwien.thesis.caddc.data.parse.types.impl.HTMLTableParserFinland;
import at.ac.tuwien.thesis.caddc.data.parse.types.impl.HTMLTableParserSweden;
import at.ac.tuwien.thesis.caddc.model.Location;
import at.ac.tuwien.thesis.caddc.persistence.ImportDataException;
import at.ac.tuwien.thesis.caddc.rest.client.RESTClient;



/**
 * Defines a MarketData Instance responsible for retrieving energy
 * market data from Sweden for different data sources
 */
public class MarketDataSweden extends MarketData {
	
	/**
	 * Create a MarketData Instance with the given location
	 * @param location the location for this MarketData Instance
	 */
	public MarketDataSweden(Location location) {
		super(location, 
			new DataFetcherSweden(),
			new ParserSweden());
	}	
}
