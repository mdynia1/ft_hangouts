package com.example.hangouts;

import java.util.ArrayList;
import java.util.List;

public class Country_code{
    private String country;
    private String code;

    public static List<Country_code> country_codes =  new ArrayList<Country_code>() ;

    public Country_code(String country, String code){
        this.code = code;
        this.country = country;
    }


    public static void setCountry_codes()
    {
        country_codes.add(new Country_code("AC", "+247"));
        country_codes.add(new Country_code("AD", "+376"));
        country_codes.add(new Country_code("AE", "+971"));
        country_codes.add(new Country_code("AF", "+93"));
        country_codes.add(new Country_code("AG", "+1-268"));
        country_codes.add(new Country_code("AI", "+1-264"));
        country_codes.add(new Country_code("AL", "+355"));
        country_codes.add(new Country_code("AM", "+374"));
        country_codes.add(new Country_code("AN", "+599"));
        country_codes.add(new Country_code("AO", "+244"));
        country_codes.add(new Country_code("AR", "+54"));
        country_codes.add(new Country_code("AS", "+1-684"));
        country_codes.add(new Country_code("AT", "+43"));
        country_codes.add(new Country_code("AU", "+61"));
        country_codes.add(new Country_code("AW", "+297"));
        country_codes.add(new Country_code("AX", "+358-18"));
        country_codes.add(new Country_code("AZ", "+374-97"));
        country_codes.add(new Country_code("AZ", "+994"));
        country_codes.add(new Country_code("BA", "+387"));
        country_codes.add(new Country_code("BB", "+1-246"));
        country_codes.add(new Country_code("BD", "+880"));
        country_codes.add(new Country_code("BE", "+32"));
        country_codes.add(new Country_code("BF", "+226"));
        country_codes.add(new Country_code("BG", "+359"));
        country_codes.add(new Country_code("BH", "+973"));
        country_codes.add(new Country_code("BI", "+257"));
        country_codes.add(new Country_code("BJ", "+229"));
        country_codes.add(new Country_code("BM", "+1-441"));
        country_codes.add(new Country_code("BN", "+673"));
        country_codes.add(new Country_code("BO", "+591"));
        country_codes.add(new Country_code("BR", "+55"));
        country_codes.add(new Country_code("BS", "+1-242"));
        country_codes.add(new Country_code("BT", "+975"));
        country_codes.add(new Country_code("BW", "+267"));
        country_codes.add(new Country_code("BY", "+375"));
        country_codes.add(new Country_code("BZ", "+501"));
        country_codes.add(new Country_code("CA", "+1"));
        country_codes.add(new Country_code("CC", "+61"));
        country_codes.add(new Country_code("CD", "+243"));
        country_codes.add(new Country_code("CF", "+236"));
        country_codes.add(new Country_code("CG", "+242"));
        country_codes.add(new Country_code("CH", "+41"));
        country_codes.add(new Country_code("CI", "+225"));
        country_codes.add(new Country_code("CK", "+682"));
        country_codes.add(new Country_code("CL", "+56"));
        country_codes.add(new Country_code("CM", "+237"));
        country_codes.add(new Country_code("CN", "+86"));
        country_codes.add(new Country_code("CO", "+57"));
        country_codes.add(new Country_code("CR", "+506"));
        country_codes.add(new Country_code("CS", "+381"));
        country_codes.add(new Country_code("CU", "+53"));
        country_codes.add(new Country_code("CV", "+238"));
        country_codes.add(new Country_code("CX", "+61"));
        country_codes.add(new Country_code("CY", "+90-392"));
        country_codes.add(new Country_code("CY", "+357"));
        country_codes.add(new Country_code("CZ", "+420"));
        country_codes.add(new Country_code("DE", "+49"));
        country_codes.add(new Country_code("DJ", "+253"));
        country_codes.add(new Country_code("DK", "+45"));
        country_codes.add(new Country_code("DM", "+1-767"));
        country_codes.add(new Country_code("DO", "+1-809")); // and 1-829?
        country_codes.add(new Country_code("DZ", "+213"));
        country_codes.add(new Country_code("EC", "+593"));
        country_codes.add(new Country_code("EE", "+372"));
        country_codes.add(new Country_code("EG", "+20"));
        country_codes.add(new Country_code("EH", "+212"));
        country_codes.add(new Country_code("ER", "+291"));
        country_codes.add(new Country_code("ES", "+34"));
        country_codes.add(new Country_code("ET", "+251"));
        country_codes.add(new Country_code("FI", "+358"));
        country_codes.add(new Country_code("FJ", "+679"));
        country_codes.add(new Country_code("FK", "+500"));
        country_codes.add(new Country_code("FM", "+691"));
        country_codes.add(new Country_code("FO", "+298"));
        country_codes.add(new Country_code("FR", "+33"));
        country_codes.add(new Country_code("GA", "+241"));
        country_codes.add(new Country_code("GB", "+44"));
        country_codes.add(new Country_code("GD", "+1-473"));
        country_codes.add(new Country_code("GE", "+995"));
        country_codes.add(new Country_code("GF", "+594"));
        country_codes.add(new Country_code("GG", "+44"));
        country_codes.add(new Country_code("GH", "+233"));
        country_codes.add(new Country_code("GI", "+350"));
        country_codes.add(new Country_code("GL", "+299"));
        country_codes.add(new Country_code("GM", "+220"));
        country_codes.add(new Country_code("GN", "+224"));
        country_codes.add(new Country_code("GP", "+590"));
        country_codes.add(new Country_code("GQ", "+240"));
        country_codes.add(new Country_code("GR", "+30"));
        country_codes.add(new Country_code("GT", "+502"));
        country_codes.add(new Country_code("GU", "+1-671"));
        country_codes.add(new Country_code("GW", "+245"));
        country_codes.add(new Country_code("GY", "+592"));
        country_codes.add(new Country_code("HK", "+852"));
        country_codes.add(new Country_code("HN", "+504"));
        country_codes.add(new Country_code("HR", "+385"));
        country_codes.add(new Country_code("HT", "+509"));
        country_codes.add(new Country_code("HU", "+36"));
        country_codes.add(new Country_code("ID", "+62"));
        country_codes.add(new Country_code("IE", "+353"));
        country_codes.add(new Country_code("IL", "+972"));
        country_codes.add(new Country_code("IM", "+44"));
        country_codes.add(new Country_code("IN", "+91"));
        country_codes.add(new Country_code("IO", "+246"));
        country_codes.add(new Country_code("IQ", "+964"));
        country_codes.add(new Country_code("IR", "+98"));
        country_codes.add(new Country_code("IS", "+354"));
        country_codes.add(new Country_code("IT", "+39"));
        country_codes.add(new Country_code("JE", "+44"));
        country_codes.add(new Country_code("JM", "+1-876"));
        country_codes.add(new Country_code("JO", "+962"));
        country_codes.add(new Country_code("JP", "+81"));
        country_codes.add(new Country_code("KE", "+254"));
        country_codes.add(new Country_code("KG", "+996"));
        country_codes.add(new Country_code("KH", "+855"));
        country_codes.add(new Country_code("KI", "+686"));
        country_codes.add(new Country_code("KM", "+269"));
        country_codes.add(new Country_code("KN", "+1-869"));
        country_codes.add(new Country_code("KP", "+850"));
        country_codes.add(new Country_code("KR", "+82"));
        country_codes.add(new Country_code("KW", "+965"));
        country_codes.add(new Country_code("KY", "+1-345"));
        country_codes.add(new Country_code("KZ", "+7"));
        country_codes.add(new Country_code("LA", "+856"));
        country_codes.add(new Country_code("LB", "+961"));
        country_codes.add(new Country_code("LC", "+1-758"));
        country_codes.add(new Country_code("LI", "+423"));
        country_codes.add(new Country_code("LK", "+94"));
        country_codes.add(new Country_code("LR", "+231"));
        country_codes.add(new Country_code("LS", "+266"));
        country_codes.add(new Country_code("LT", "+370"));
        country_codes.add(new Country_code("LU", "+352"));
        country_codes.add(new Country_code("LV", "+371"));
        country_codes.add(new Country_code("LY", "+218"));
        country_codes.add(new Country_code("MA", "+212"));
        country_codes.add(new Country_code("MC", "+377"));
        country_codes.add(new Country_code("MD", "+373-533"));
        country_codes.add(new Country_code("MD", "+373"));
        country_codes.add(new Country_code("ME", "+382"));
        country_codes.add(new Country_code("MG", "+261"));
        country_codes.add(new Country_code("MH", "+692"));
        country_codes.add(new Country_code("MK", "+389"));
        country_codes.add(new Country_code("ML", "+223"));
        country_codes.add(new Country_code("MM", "+95"));
        country_codes.add(new Country_code("MN", "+976"));
        country_codes.add(new Country_code("MO", "+853"));
        country_codes.add(new Country_code("MP", "+1-670"));
        country_codes.add(new Country_code("MQ", "+596"));
        country_codes.add(new Country_code("MR", "+222"));
        country_codes.add(new Country_code("MS", "+1-664"));
        country_codes.add(new Country_code("MT", "+356"));
        country_codes.add(new Country_code("MU", "+230"));
        country_codes.add(new Country_code("MV", "+960"));
        country_codes.add(new Country_code("MW", "+265"));
        country_codes.add(new Country_code("MX", "+52"));
        country_codes.add(new Country_code("MY", "+60"));
        country_codes.add(new Country_code("MZ", "+258"));
        country_codes.add(new Country_code("NA", "+264"));
        country_codes.add(new Country_code("NC", "+687"));
        country_codes.add(new Country_code("NE", "+227"));
        country_codes.add(new Country_code("NF", "+672"));
        country_codes.add(new Country_code("NG", "+234"));
        country_codes.add(new Country_code("NI", "+505"));
        country_codes.add(new Country_code("NL", "+31"));
        country_codes.add(new Country_code("NO", "+47"));
        country_codes.add(new Country_code("NP", "+977"));
        country_codes.add(new Country_code("NR", "+674"));
        country_codes.add(new Country_code("NU", "+683"));
        country_codes.add(new Country_code("NZ", "+64"));
        country_codes.add(new Country_code("OM", "+968"));
        country_codes.add(new Country_code("PA", "+507"));
        country_codes.add(new Country_code("PE", "+51"));
        country_codes.add(new Country_code("PF", "+689"));
        country_codes.add(new Country_code("PG", "+675"));
        country_codes.add(new Country_code("PH", "+63"));
        country_codes.add(new Country_code("PK", "+92"));
        country_codes.add(new Country_code("PL", "+48"));
        country_codes.add(new Country_code("PM", "+508"));
        country_codes.add(new Country_code("PR", "+1-787")); // and 1-939 ?
        country_codes.add(new Country_code("PS", "+970"));
        country_codes.add(new Country_code("PT", "+351"));
        country_codes.add(new Country_code("PW", "+680"));
        country_codes.add(new Country_code("PY", "+595"));
        country_codes.add(new Country_code("QA", "+974"));
        country_codes.add(new Country_code("RE", "+262"));
        country_codes.add(new Country_code("RO", "+40"));
        country_codes.add(new Country_code("RS", "+381"));
        country_codes.add(new Country_code("RU", "+7"));
        country_codes.add(new Country_code("RW", "+250"));
        country_codes.add(new Country_code("SA", "+966"));
        country_codes.add(new Country_code("SB", "+677"));
        country_codes.add(new Country_code("SC", "+248"));
        country_codes.add(new Country_code("SD", "+249"));
        country_codes.add(new Country_code("SE", "+46"));
        country_codes.add(new Country_code("SG", "+65"));
        country_codes.add(new Country_code("SH", "+290"));
        country_codes.add(new Country_code("SI", "+386"));
        country_codes.add(new Country_code("SJ", "+47"));
        country_codes.add(new Country_code("SK", "+421"));
        country_codes.add(new Country_code("SL", "+232"));
        country_codes.add(new Country_code("SM", "+378"));
        country_codes.add(new Country_code("SN", "+221"));
        country_codes.add(new Country_code("SO", "+252"));
        country_codes.add(new Country_code("SO", "+252"));
        country_codes.add(new Country_code("SR", "+597"));
        country_codes.add(new Country_code("ST", "+239"));
        country_codes.add(new Country_code("SV", "+503"));
        country_codes.add(new Country_code("SY", "+963"));
        country_codes.add(new Country_code("SZ", "+268"));
        country_codes.add(new Country_code("TA", "+290"));
        country_codes.add(new Country_code("TC", "+1-649"));
        country_codes.add(new Country_code("TD", "+235"));
        country_codes.add(new Country_code("TG", "+228"));
        country_codes.add(new Country_code("TH", "+66"));
        country_codes.add(new Country_code("TJ", "+992"));
        country_codes.add(new Country_code("TK", "+690"));
        country_codes.add(new Country_code("TL", "+670"));
        country_codes.add(new Country_code("TM", "+993"));
        country_codes.add(new Country_code("TN", "+216"));
        country_codes.add(new Country_code("TO", "+676"));
        country_codes.add(new Country_code("TR", "+90"));
        country_codes.add(new Country_code("TT", "+1-868"));
        country_codes.add(new Country_code("TV", "+688"));
        country_codes.add(new Country_code("TW", "+886"));
        country_codes.add(new Country_code("TZ", "+255"));
        country_codes.add(new Country_code("UA", "+380"));
        country_codes.add(new Country_code("UG", "+256"));
        country_codes.add(new Country_code("US", "+1"));
        country_codes.add(new Country_code("UY", "+598"));
        country_codes.add(new Country_code("UZ", "+998"));
        country_codes.add(new Country_code("VA", "+379"));
        country_codes.add(new Country_code("VC", "+1-784"));
        country_codes.add(new Country_code("VE", "+58"));
        country_codes.add(new Country_code("VG", "+1-284"));
        country_codes.add(new Country_code("VI", "+1-340"));
        country_codes.add(new Country_code("VN", "+84"));
        country_codes.add(new Country_code("VU", "+678"));
        country_codes.add(new Country_code("WF", "+681"));
        country_codes.add(new Country_code("WS", "+685"));
        country_codes.add(new Country_code("YE", "+967"));
        country_codes.add(new Country_code("YT", "+262"));
        country_codes.add(new Country_code("ZA", "+27"));
        country_codes.add(new Country_code("ZM", "+260"));
        country_codes.add(new Country_code("ZW", "+263"));
    }

    public static String get_Countrycode(String country){
        for (Country_code country_code : country_codes){
            String thisCountry = country_code.get_Country();
            if (thisCountry.equals(country))
                return country_code.get_code();
        }
        return ("+380");
    }

    public String get_code(){
        return this.code;
    }

    public String get_Country() {
        return this.country;
    }

    public static String get_Country(String code){
        for (Country_code country_code : country_codes){
            String thisCountry = country_code.get_code();
            if (code.contains(thisCountry)) {
                code = code.substring(thisCountry.length());
                return code;//country_code.get_Country();
            }
        }
        return ("");
    }

    public static String get_Country_code(String code){
        for (Country_code country_code : country_codes){
            String thisCountry = country_code.get_code();
            if (code.contains(thisCountry)) {
                // code = code.substring(thisCountry.length());
                return thisCountry;//country_code.get_Country();
            }
        }
        return ("");
    }
}
