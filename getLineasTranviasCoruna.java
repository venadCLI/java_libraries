private ArrayList<String> getLineasTranviasCoruna() {
    ArrayList<String> lineasAutobus = new ArrayList<>();
    String url_lineas = "http://www.tranviascoruna.com/lineas-y-horarios/";
    try {
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
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return lineasAutobus;
}
