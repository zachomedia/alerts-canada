import ca.zacharyseguin.alertscanada.*;
import ca.zacharyseguin.util.geo.*;

import java.io.*;
import java.net.URL;
import com.google.gson.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.lang.reflect.*;

public class AlertsCanadaTester
{
    public static void main (String[] args) throws Exception
    {
        final Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(GregorianCalendar.class, new JsonSerializer<GregorianCalendar>() {
                        public JsonElement serialize(GregorianCalendar date, Type type, JsonSerializationContext context) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                            return new JsonPrimitive(sdf.format(date.getTime()));
                        }
                    })
                    .create();

        AlertsCanada ac = new AlertsCanada();
        ac.addAlertsListener(new AlertsListener() {
            public void alertReceived(Alert alert) {
                System.out.println(gson.toJson(alert));
            }

            public void alertUpdated(Alert newAlert, Alert oldAlert) {
                System.out.println(gson.toJson(newAlert));
            }

            public void alertEnded(Alert alert) {
                System.out.println(gson.toJson(alert));
            }
        });

        ac.parseAlert("<?xml version=\"1.0\" encoding=\"UTF-8\"?><alert xmlns=\"urn:oasis:names:tc:emergency:cap:1.2\"><identifier>06FBA59A-86AD-35C8-A871-9DF9B8F3594D</identifier><sender>NAADS-Testing@NAADS@PelmorexCommunications(Testing)</sender><sent>2011-06-30T06:40:44+00:00</sent><status>Actual</status><msgType>Alert</msgType><scope>Public</scope><code>profile:CAP-CP:0.3</code><references>cap-pac@ec.gc.ca,2.49.0.1.124.fc81859d.2014,2014-08-18T23:41:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.6a9259eb.2014,2014-08-18T23:41:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.d2b36070.2014,2014-08-19T00:15:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.95c99b5a.2014,2014-08-19T00:15:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.4bdda0d4.2014,2014-08-19T00:17:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.93b61787.2014,2014-08-19T00:17:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.32bfe11d.2014,2014-08-19T00:45:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.9fa72e5c.2014,2014-08-19T00:45:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.d63af3e1.2014,2014-08-19T01:01:00-00:00 cap-pac@ec.gc.ca,2.49.0.1.124.b74dafb8.2014,2014-08-19T01:01:00-00:00</references><info><language>en-CA</language><category>Met</category><event>Tornado</event><responseType>None</responseType><urgency>Immediate</urgency><severity>Extreme</severity><certainty>Observed</certainty><eventCode><valueName>profile:CAP-CP:Event:0.3</valueName><value>tornado</value></eventCode><expires>2011-07-01T13:00:00+00:00</expires><senderName>Pelmorex Communications (Testing)</senderName><headline>Sample Alert Message No Attachment</headline><description>This example contains an Alert Message with NAAD System Signature and without an attachment.</description><area><areaDesc>Crane river Manitoba</areaDesc><polygon>51.552994,-99.216119 51.564488,-99.219607 51.575448,-99.219861 51.576326,-99.221583 51.559342,-99.225395 51.552488,-99.236193 51.538291,-99.244281 51.529917,-99.245778 51.528724,-99.256742 51.523192,-99.261972 51.516722,-99.263953 51.510717,-99.263414 51.507501,-99.265974 51.504579,-99.265929 51.502849,-99.263657 51.498802,-99.26395 51.497321,-99.266635 51.49194,-99.264386 51.490075,-99.266247 51.479374,-99.262484 51.473999,-99.265706 51.474256,-99.204523 51.503636,-99.204828 51.503875,-99.193096 51.532352,-99.192697 51.535124,-99.19768 51.537876,-99.19726 51.553234,-99.202605 51.549832,-99.209311 51.548486,-99.207949 51.543643,-99.210667 51.545245,-99.215015 51.551858,-99.217815 51.551197,-99.215461 51.552994,-99.216119</polygon><geocode><valueName>profile:CAP-CP:Location:0.3</valueName><value>4619068</value></geocode></area></info><Signature Id=\"NAADS Signature\" xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\" /><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\" /><Reference URI=\"\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\" /></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\" /><DigestValue>tRtS1Z61kNpZDbdKoFfxBPQyulA=</DigestValue></Reference></SignedInfo><SignatureValue>FYsFoQ/hNIEUKsLeZDb/2TKPzCHP5bQggdGaSFOb26lzCy8E2YHBRzGG0ON/tkERNP6CfX+DEY6XM4YmCzxU/OHzml+I0njQ+ohhOCq61LOQ9jJ5kBEARU8m17zG8ffzCGTUObTBV7UHP7BYIVLxQeH+8APYpYFRKP0haJQJskD627vAf6lGuwxjveaOLwyXNeHeqaS1C8iR+CEPMSyR6h4sWQXyvKYAuKjI1JPvSlIaG1xznyHRLEfl5K2xWy5MH3dSKZ9PIEf9okIF4nqAZJ1KO3Q6/qeVmwI1PWuiiCysjTOlRy7rDBRYIaxU7sNIbHo8YClxUN9sMaHkK5kSow==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIFazCCBFOgAwIBAgIQBO1UglUDPqeqqRrvrn+2JTANBgkqhkiG9w0BAQUFADCB3TELMAkGA1UEBhMCVVMxFzAVBgNVBAoTDlZlcmlTaWduLCBJbmMuMR8wHQYDVQQLExZWZXJpU2lnbiBUcnVzdCBOZXR3b3JrMTswOQYDVQQLEzJUZXJtcyBvZiB1c2UgYXQgaHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL3JwYSAoYykwOTEeMBwGA1UECxMVUGVyc29uYSBOb3QgVmFsaWRhdGVkMTcwNQYDVQQDEy5WZXJpU2lnbiBDbGFzcyAxIEluZGl2aWR1YWwgU3Vic2NyaWJlciBDQSAtIEczMB4XDTExMDUwMzAwMDAwMFoXDTEyMDUwNTIzNTk1OVowggEYMRcwFQYDVQQKEw5WZXJpU2lnbiwgSW5jLjEfMB0GA1UECxMWVmVyaVNpZ24gVHJ1c3QgTmV0d29yazFGMEQGA1UECxM9d3d3LnZlcmlzaWduLmNvbS9yZXBvc2l0b3J5L1JQQSBJbmNvcnAuIGJ5IFJlZi4sTElBQi5MVEQoYyk5ODEeMBwGA1UECxMVUGVyc29uYSBOb3QgVmFsaWRhdGVkMTMwMQYDVQQLEypEaWdpdGFsIElEIENsYXNzIDEgLSBOZXRzY2FwZSBGdWxsIFNlcnZpY2UxGzAZBgNVBAMUEk5BQURTIERTUyBQZWxtb3JleDEiMCAGCSqGSIb3DQEJARYTbmV0b3BzQHBlbG1vcmV4LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAK305DHFqHh9MnB0pzoBBRvck/7XLTbyDjREb++kY/+K9eoi8dwpH4UIo9HRFLq6WAUjsq24082PysoFk4j13IC0w4bHbfhf2k47e5BQGR+/sGE0kNLziSg2I/2XErMt0+Yb/ZtMGL5GdNr+YNunlYahdhWQdA4Sefio++r3I3TGRyw9cbhYi0xhHFdVWfr/r0lc1RrIrwEiqMA34TNIjYknk77ODTvXSDOlQ/SxcXRbkAstT6R9GuERzFrTLHU8DhYEThhPiRwi1R4xU05tzVy6Sxt4O1JHsY3OV8npBnfF6ErG1U1SH6fWiYu9S5c8JDeheit75TwyV49R+RlaxEMCAwEAAaOB6DCB5TAJBgNVHRMEAjAAMEQGA1UdIAQ9MDswOQYLYIZIAYb4RQEHFwEwKjAoBggrBgEFBQcCARYcaHR0cHM6Ly93d3cudmVyaXNpZ24uY29tL3JwYTALBgNVHQ8EBAMCBaAwHQYDVR0lBBYwFAYIKwYBBQUHAwQGCCsGAQUFBwMCMBQGCmCGSAGG+EUBBgcEBhYETm9uZTBQBgNVHR8ESTBHMEWgQ6BBhj9odHRwOi8vaW5kYzFkaWdpdGFsaWQtZzMtY3JsLnZlcmlzaWduLmNvbS9JbmRDMURpZ2l0YWxJRC1HMy5jcmwwDQYJKoZIhvcNAQEFBQADggEBAD3o5bsnBvkF5zkd1A+nEf3yXOJPhjWTzswwklAFSwo1FiDcRpBzd98+SjBFs7hG2VuP+5qxudLxCP2vC6x8HnQAXjmSPx1d95V8UUk0SLaABSHNri85AalpQR4R7t9Vr2hrY0F0T7ft0IvclaRu0B4zUX4uHYFYyuI9knyJtomAb4HyVSeKpXA4fg87bFK3h94z9p4+l+44nSWia1MKrpQarjQj9TCxZgHN1JEMQtpWNEWeRS+pg4rymNHvq9ZvYOEQ6LXWgVuqzUB1rFIntQxIGH4gzIso4mOZVqyTQ8cZDp0CFQh7WUIroZyruOlvD8+erZCijYVjeXtotwESCgY=</X509Certificate></X509Data></KeyInfo><Object xmlns=\"\"><SignatureProperties><SignatureProperty Id=\"NAADS-DSS1\" Target=\"http://dss1-staging.naad-adna.pelmorex.com\"><xc:value xmlns:xc=\"http://docs.oasis-open.org/emergency/cap/v1.2/CAP-v1.2.xsd\" /></SignatureProperty><SignatureProperty Id=\"NAADS-DSS2\" Target=\"http://dss2-staging.naad-adna.pelmorex.com\"><xc:value xmlns:xc=\"http://docs.oasis-open.org/emergency/cap/v1.2/CAP-v1.2.xsd\" /></SignatureProperty></SignatureProperties></Object></Signature></alert>");

        //while (true) {
        //    Thread.sleep(500);
        //}

        /*AlertArea alertArea = new AlertArea.Builder()
                                            .description("Test Area")
                                            .circle(new Circle())
                                            .ceiling(0.0)
                                            .build();

        AlertResource alertResource = new AlertResource.Builder()
                                                        .description("Test Resource")
                                                        .mimeType("text/html")
                                                        .uri(new URL("https://zacharyseguin.ca/"))
                                                        .build();

        AlertInfo alertInfo = new AlertInfo.Builder()
                                            .language("en-CA")
                                            .categories(Arrays.asList(new AlertCategory[]{
                                                AlertCategory.OTHER
                                            }))
                                            .event("TEST EVENT")
                                            .responseTypes(Arrays.asList(new AlertResponseType[]{
                                                AlertResponseType.NONE
                                            }))
                                            .severity(AlertSeverity.MINOR)
                                            .urgency(AlertUrgency.EXPECTED)
                                            .certainty(AlertCertainty.LIKELY)
                                            .audience("TEST ONLY")
                                            .effective(GregorianCalendar.getInstance())
                                            .onset(GregorianCalendar.getInstance())
                                            .expires(GregorianCalendar.getInstance())
                                            .senderName("Zachary Seguin")
                                            .headline("TEST EVENT ONLY")
                                            .description("This is only a test. No threat is present.")
                                            .instruction("TEST ONLY -- NO ACTION REQUIRED")
                                            .web(new URL("https://zacharyseguin.ca/"))
                                            .contact("Zachary Seguin")
                                            .resources(Arrays.asList(new AlertResource[] {
                                                alertResource
                                            }))
                                            .areas(Arrays.asList(new AlertArea[] {
                                                alertArea
                                            }))
                                            .build();

        Alert alert = new Alert.Builder()
                                .identifier("ZS-0001")
                                .sender("Zachary Seguin")
                                .sent(GregorianCalendar.getInstance())
                                .status(AlertStatus.TEST)
                                .type(AlertType.ALERT)
                                .scope(AlertScope.PUBLIC)
                                .restriction("TEST ONLY")
                                .addresses(Arrays.asList(new String[]{ "Zachary Seguin" }))
                                .note("TEST ONLY")
                                .information(Arrays.asList(new AlertInfo[] {
                                    alertInfo
                                }))
                                .build();

        Gson gson = new GsonBuilder()
                    .serializeNulls()
                    .registerTypeAdapter(GregorianCalendar.class, new JsonSerializer<GregorianCalendar>() {
                        public JsonElement serialize(GregorianCalendar date, Type type, JsonSerializationContext context) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                            return new JsonPrimitive(sdf.format(date.getTime()));
                        }
                    })
                    .create();
        System.out.println(gson.toJson(alert));*/
    }// End of main method
}// End of class
