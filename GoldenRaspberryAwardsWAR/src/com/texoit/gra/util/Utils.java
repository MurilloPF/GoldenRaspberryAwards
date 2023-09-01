package com.texoit.gra.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.rpc.ServiceException;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class Utils {
	private static final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	private static final DateFormat DATE_FORMATTER_YEAR_TWO_CHAR = new SimpleDateFormat("dd/MM/yy");
	private static final DateFormat TS_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static final DateFormat TS_FORMATTER2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat TS_FORMATTER3 = new SimpleDateFormat("HH:mm:ss");

	public static String formata(String valor) {
		return (valor == null) ? "-" : valor;
	}

	public static String safeTrim(String texto) {
		if(texto == null) 
			return texto;
		else
			return texto.trim();
	}

	public static String substituiCaracter(String texto, String trocar, String por) {
		String retorno = "";
		
		if(texto == null) return texto;
		
		for (int i = 0; i < texto.length(); i++) {
			if (texto.substring(i, i+1).equals(trocar))
				retorno += por;
			else
				retorno += texto.substring(i, i+1);
		}
		
		if (retorno.length() > 1 && retorno.substring(0, 1).equals(","))
			return "0" + retorno;
		
		return retorno;
	}

	public static String substituiCaracter(String texto, String trocar, String por, int casas) {
		if (texto == null) return texto;
		
		String retorno = "";
		
		for (int i = 0; i < texto.length(); i++) {
			if (texto.substring(i, i+1).equals(trocar)&& casas == texto.length() - (i+1))
				retorno += por;
			else
				retorno += texto.substring(i, i+1);
		}
		
		if (retorno.length() > 1 && retorno.substring(0, 1).equals(","))
			return "0" + retorno;
		
		return retorno;
	}
	
	public static String formatarDecimal(Double valor, int casas) { 
		return formatarDecimal(new BigDecimal(valor) ,casas);
	}

    public static String formatarDecimal(BigDecimal valor, int casas) {        
        if(ehVazio(valor) || valor.equals(new BigDecimal("0")))
            return "0";
        
        valor = valor.setScale(casas, RoundingMode.HALF_UP);
        
        StringBuilder formato = new StringBuilder("0");
        if(casas > 0)
            formato.append(".");
        
        for(int i = 0; i < casas; i++)
            formato.append("#");
        
        DecimalFormat decFormat = new DecimalFormat(formato.toString());
        decFormat.setMinimumFractionDigits(casas);
        
        return decFormat.format(valor);
    }

    public static String formatarDecimalZero(BigDecimal valor, int casas) {        
        if(ehVazio(valor))
            valor = new BigDecimal(0);
       
        valor = valor.setScale(casas, RoundingMode.HALF_UP);
        
        StringBuilder formato = new StringBuilder("0");
        if(casas > 0)
            formato.append(".");
        
        for(int i = 0; i < casas; i++)
            formato.append("#");
        
        DecimalFormat decFormat = new DecimalFormat(formato.toString());
        decFormat.setMinimumFractionDigits(casas);
        
        return decFormat.format(valor);
    }
	
	public static String formatarDecimalRetornaVazioSeZero(BigDecimal valor, int casas){
		String retorno = formatarDecimal(valor, casas);
		if("0".equals(retorno))
			return "";
		else
			return retorno;
	}

    public static String formataTimeEmString(Date data){
        if(data != null)
            return TS_FORMATTER3.format(data);
        else
            return "";
    }

    public static String formataTimestampEmString(Date data){
        if(data != null)
            return TS_FORMATTER.format(data);
        else
            return "";
    }

    public static String formataTimestampEmStringNull(Date data){
        if(data != null)
            return TS_FORMATTER.format(data);
        else
            return null;
    }

	public static Date formataStringEmTimestamp(String data){
		try{
			if(!Utils.ehVazio(data))
				return TS_FORMATTER.parse(data);
			return new Date();
		}catch (ParseException e) {
			return new Date();
		}
	}

	public static Date formataStringEmTimestamp2(String data){
		try{
			if(!Utils.ehVazio(data))
				return TS_FORMATTER2.parse(data);
			return new Date();
		}catch (ParseException e) {
			return new Date();
		}
	}

	public static String formataDataEmString(Date data){
		if(data != null)
			return DATE_FORMATTER.format(data);
		else
			return "";
	}

    public static String formataDataEmStringNull(Date data){
        if(data != null)
            return DATE_FORMATTER.format(data);
        else
            return null;
    }

	public static String formataDataYearTwoCharEmString(Date data){
		if(data != null)
			return DATE_FORMATTER_YEAR_TWO_CHAR.format(data);
		else
			return "";
	}
	
	public static String formataDuracao(Duration duration) {
		return DurationFormatUtils.formatDurationHMS(duration.toMillis());
	}
	
	public static XMLGregorianCalendar formataStringEmGC(String data) throws ParseException, DatatypeConfigurationException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = format.parse(data);
	
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
	}
	
    public static Date formataStringEmData(String data){
        try {
            if(!Utils.ehVazio(data))
                return DATE_FORMATTER.parse(data);
            
            return new Date();
        } catch (ParseException e) {
            return new Date();
        }
    }
    
    public static Date formataStringEmDataNull(String data){
        try {
            if(!Utils.ehVazio(data))
                return DATE_FORMATTER.parse(data);
            
            return null;
        } catch (ParseException e) {
            return new Date();
        }
    }	

	/**
	 * Modifica a data indicada na String de acordo com o total de dias indicado.
	 * Exemplo:
	 * totalDias = -1
	 * data = 02/10/2010
	 * retorna java.util.Date = 01/10/2010
	 * 
	 * @param int totalDias
	 * @param String data
	 * @return Data
	 */
	public static Date adcionaOuSubtraiDia(int totalDias, String data){
		if(data == null || data.trim().equals("")) return new Date();
		
		Date dtData = formataStringEmData(data);
		Calendar clData = Calendar.getInstance();
		clData.setTime(dtData);
		clData.add(Calendar.DAY_OF_MONTH, totalDias);
		return clData.getTime();
	}
	
	public static Date adcionaOuSubtraiDia(int totalDias, Date data){
		if(data == null) return new Date();

		Calendar clData = Calendar.getInstance();
		clData.setTime(data);
		clData.add(Calendar.DAY_OF_MONTH, totalDias);
		return clData.getTime();
	}

	public static boolean ehDataEntre(String dataComp, String dataIni, String dataFim) {
		if(dataComp == null || dataComp.equals("")) return false;
		if(dataIni == null || dataIni.equals("")) return false;
		if(dataFim == null || dataFim.equals("")) dataFim = formataDataEmString(new Date());
		
		Date dtComp;
		Date dtMenor;
		Date dtMaior;
		
		try {
			dtComp = DATE_FORMATTER.parse(dataComp);
			dtMenor = DATE_FORMATTER.parse(dataIni);
			dtMaior = DATE_FORMATTER.parse(dataFim);
		} catch (ParseException e) {
			return false;
		}
		
		boolean retorno = (dtMenor.before(dtComp) || dtMenor.equals(dtComp)) && (dtMaior.after(dtComp) || dtMaior.equals(dtComp));
		
		return retorno;
	}

	public static boolean ehDataMaior(String dataMaior, String dataMenor) throws ServiceException{
		if(dataMenor == null || dataMenor.equals("")) return false;
		if(dataMaior == null || dataMaior.equals("")) return false;
		
		try {
			Date dtMaior = DATE_FORMATTER.parse(dataMaior);
			Date dtMenor = DATE_FORMATTER.parse(dataMenor);
			boolean retorno = (dtMaior.after(dtMenor));
			
			return retorno;
		} catch (Exception e) {
			throw new ServiceException("error.sistema", e);
		}
	}

	public static boolean ehDataIgual(String data1, String data2) throws ServiceException{
		if(data2 == null || data2.equals("")) return false;
		if(data1 == null || data1.equals("")) return false;
		
		try {
			Date dt1 = DATE_FORMATTER.parse(data1);
			Date dt2 = DATE_FORMATTER.parse(data2);
			boolean retorno = dt1.equals(dt2);
			
			return retorno;
		} catch (Exception e) {
			throw new ServiceException("error.sistema", e);
		}
	}

	public static boolean ehDataMaiorOuIgual(String dataMaior, String dataMenor) throws ServiceException{
		if(dataMenor == null || dataMenor.equals("")) return false;
		if(dataMaior == null || dataMaior.equals("")) return false;
		
		try {
			Date dtMaior = DATE_FORMATTER.parse(dataMaior);
			Date dtMenor = DATE_FORMATTER.parse(dataMenor);
			boolean retorno = !(dtMaior.before(dtMenor));
			
			return retorno;
		} catch (Exception e) {
			throw new ServiceException("error.sistema", e);
		}
	}

    public static Long convertToLong(String s, long defaultValue){
        if(s == null || s.trim().equals("")) return defaultValue;
        
        try {
           return Long.parseLong(s);
        } catch (Exception e) {
           return defaultValue;
        }
    }

    public static BigDecimal convertToBigDecimal(String x){
        if(x == null || "".equals(x)) return new BigDecimal("0");
        
        String stX = substituiCaracter(x, ",", ".");
        
        return new BigDecimal(stX);
    }

    public static BigDecimal convertToBigDecimalSafe(String x){
        if(x == null || "".equals(x.trim()) || !isParsableToBigDecimal(x)) return new BigDecimal("0");
        
        String stX = substituiCaracter(x, ",", ".");
        
        return new BigDecimal(stX);
    }
    
    public static int stringToIntSafe(String param) {
        for(char c : param.toCharArray()) {
                if(!Character.isDigit(c))
                        return -1;
        }
        
        return Integer.valueOf(param);
    }
    
    public static Integer convertToInteger(String x){
        if(x == null || "".equals(x)) return new Integer("0");
        
        String stX = substituiCaracter(x, ",", "");
        
        return Integer.parseInt(stX);
    }

    public static Double convertToDouble(String x){
        if(x == null || "".equals(x)) return new Double("0");
        
        String stX = substituiCaracter(x, ",", ".");
        
        return Double.parseDouble(stX);
    }

    public static Double convertToDoubleSafe(String x){
        if(x == null || "".equals(x.trim()) || !isParsableToDouble(x)) return new Double("0");
        
        String stX = substituiCaracter(x, ",", ".");
        
        return Double.parseDouble(stX);
    }

    public static Double convertToDoubleNull(String x){
        if(x == null || "".equals(x)) return null;
        
        String stX = substituiCaracter(x, ",", ".");
        
        return Double.parseDouble(stX);
    }
    
	public static boolean isParsableToBigDecimal(String str) {
        if(str == null || "".equals(str)) return false;
        
        String stX = substituiCaracter(str, ",", ".");

        try {
			new BigDecimal(stX);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
    
	public static boolean isParsableToDouble(String str) {
        if(str == null || "".equals(str)) return false;
        
        String stX = substituiCaracter(str, ",", ".");

        try {
			new Double(stX);
			return true;
		} catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}

	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}	
	
	public static boolean isLong(String string) {
	    if(string == null || string.equals("")) {
	        return false;
	    }
	    
	    try {
	    	Long.parseLong(string);
	        return true;
	    } catch (NumberFormatException e) {
	    	return false;
	    }
	}
	
	public static String padLeftZeros(String str, int n) {
	    return String.format("%1$" + n + "s", str).replace(' ', '0');
	}	
	/**
	 * Calcula o percentual entre String
	 * @param x
	 * @param y
	 * @return (y*100)/x
	 */
	@SuppressWarnings("unused")
	public static BigDecimal calculaPercente(BigDecimal x, BigDecimal y){
		if((x == null && y == null)||
				(x.equals(new BigDecimal("0"))&& y.equals(new BigDecimal("0"))))
			return new BigDecimal("0");
		else if (y == null)
			y = new BigDecimal("0");
		else if (x == null)
			x = new BigDecimal("0");
		
		BigDecimal perc = new BigDecimal("0");
		if (!x.equals(new BigDecimal("0"))) {
			perc = y.multiply(new BigDecimal("100")).divide(x,4).movePointRight(2);
		} else if (!y.equals(new BigDecimal("0"))) {
			perc = new BigDecimal("100");
		}
		return perc;
	}
	
	/**
	 * Calcula o percentual entre String
	 * @param x
	 * @param y
	 * @return (y*100)/x
	 */
	@SuppressWarnings("unused")
	public static BigDecimal calculaPercente(BigDecimal x, BigDecimal y, int movePointRight){
		if((x == null && y == null)||
				(x.equals(new BigDecimal("0"))&& y.equals(new BigDecimal("0"))))
			return new BigDecimal("0");
		else if (y == null)
			y = new BigDecimal("0");
		else if (x == null)
			x = new BigDecimal("0");
		
		BigDecimal perc = new BigDecimal("0");
		if (!x.equals(new BigDecimal("0"))) {
			perc = y.multiply(new BigDecimal("100")).divide(x,4).movePointRight(movePointRight);
		} else if (!y.equals(new BigDecimal("0"))) {
			perc = new BigDecimal("100");
		}
		return perc;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean ehVazio(Object objeto){
		if(objeto == null)
			return true;
		if(objeto instanceof String && "".equals(objeto))
			return true;
		if(objeto instanceof String) {
			String str = ((String) objeto).trim();
			
			return str.equals("");
		}
		if(objeto instanceof BigDecimal && new BigDecimal("0").equals(objeto)) 
			return true;
		if(objeto instanceof Collection && ((Collection)objeto).isEmpty()) 
			return true;
		
		return false;
	}
	
	public static boolean ehDataValida(String dt){
		if(ehVazio(dt))
			return false;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");  
		try {  
			format.setLenient(false);  
			format.parse(dt);  
		} catch (ParseException e) {  
			  return false;
		}
		return true;
	}
	
	@SuppressWarnings("rawtypes")
	public static int getHeigthTable(Collection lista){
		int altTab = 65 +(lista.size()*30);
		if(altTab > 300)
			altTab = 300;
		return altTab;
	}

	/**
	 * Retorna Sim ou Não.
	 * @param indicador = S ou N
	 * @return
	 */
	public static String decodeIndicador(String indicador){
		if(ehVazio(indicador) || "N".equals(indicador))
			return "Não";
		return "Sim";

	}

	public static String clobToString(Clob clb) throws SQLException {
		if (clb == null) return  "";
            
		StringBuffer str = new StringBuffer();
		String strng;              
    
		BufferedReader bufferRead = new BufferedReader(clb.getCharacterStream());
   
		try {
			while ((strng=bufferRead .readLine())!=null) {
				str.append(strng);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str.toString();
    }        

    @SuppressWarnings("rawtypes")
    public static void removeRange(Collection<?> c, int start, int end) {
        if (c instanceof List) {
            removeRange((List) c, start, end);
            return;
        }

        ArrayList<?> list = new ArrayList<>(c);
        removeRange(list, start, end);
        c.retainAll(list);
    }

    public static void removeRange(Map<?, ?> map, int start, int end) {
        Set<?> keySet = map.keySet();//returns a view
        removeRange(keySet, start, end);
    }
    
    public static String truncString(String texto, int tam) {
    	if (texto == null) return null;
    	
        if (texto.length() <= tam) {
            return texto;
        } else {
            return texto.substring(0, tam);
        }
    }
}
