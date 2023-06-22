package cs3500.pa05.controller.fxadapters;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 */
public class OpenLink {

  /**
   * Handles the http link
   *
   * @param link the supplied link to handle
   */
  public static void handleLink(String link) {
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();

      try {
        URI url = new URI(link);
        desktop.browse(url);
      } catch (URISyntaxException | IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
