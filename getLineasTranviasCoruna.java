/**
 * <p>Returns every line from tranviascoruna.com website throught web parsing
 * using Jsoup.</p>
 *
 * @return {@linkplain ArrayList} of Strings with the lines on the website
 * @throws IOException if website cannot be processed correctly
 */
private ArrayList<String> getLineasTranviasCoruna() throws IOException {
    ArrayList<String> lineasAutobus = new ArrayList<>();
    String url_lineas = "http://www.tranviascoruna.com/lineas-y-horarios/";
    //Start party.
    Document doc = Jsoup.connect(url_lineas).get();
    Elements a = doc.select("a[href]");
    for (Element e : a) {
        String enlace = e.attr("href");
        String urlBase = "http://www.tranviascoruna.com/lineas-y-horarios/?linea=";
        if (enlace.contains(urlBase)) {
            String[] linea = enlace.split("=");
            if (linea[0].endsWith("linea") && !lineasAutobus.contains(linea[1])) {
                lineasAutobus.add(linea[1]);
            }
        }
    }
    if (lineasAutobus.size() == 0) {
        throw new IOException("Arraylist is empty, there's a problem with website");
    }
    return lineasAutobus;
}
