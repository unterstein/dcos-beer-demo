package io.dcos.examples.beerdemo.api;

public class BeerResponse {
  public String hostAddress;
  public String beerName;
  public String beerStyle;
  public String beerDescription;

  public BeerResponse(String hostAddress, String beerName, String beerStyle, String beerDescription) {
    this.hostAddress = hostAddress;
    this.beerName = beerName;
    this.beerStyle = beerStyle;
    this.beerDescription = beerDescription;
  }
}
