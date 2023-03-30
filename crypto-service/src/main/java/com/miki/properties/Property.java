package com.miki.properties;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.miki.constants.Const;


public class Property {
	  Logger log = Logger.getLogger(Property.class);
	/**
	 * .orElseThrow(()-> new NotFoundException("Car blue not found")
	 * https://refactorizando.com/java-optional-buenas-practicas/
	 * por defecto esta establecido el property general
	 */
	private static final long serialVersionUID = 1L;

	private	Properties property;
	private	String fileProper;

	public Property() {
		super();
		this.property = new Properties();
		this.fileProper = Const.URL_PROPERTIES;
	}

	public String getPath() {
		return Const.URL_PROPERTIES;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProperty(String key) {
		try {
			InputStream in = new FileInputStream(fileProper);
			this.property.load(in);// lo casteamos y pasamos a optional
		} catch (IOException e) {
			log.error("Propiedad no existe {}",e);
		}
		return String.valueOf(this.property.get(key));
	}

	public Optional<String> getPropertyV(String key) {
		try {
			InputStream in = new FileInputStream(fileProper);
			this.property.load(in);// lo casteamos y pasamos a optional
		} catch (IOException e) {
			log.error("Propiedad no existe {}",e);
		} // Optional.of(null) dará un NullPointer y Optional.ofNullable(null) dara un
			// Optional.empty.
		return Optional.ofNullable(String.valueOf(this.property.get(key)));
	}

	public void setKeyValueRepository(String key, String value) {
		try {
			InputStream in = new FileInputStream(fileProper);
			this.property.load(in);
			this.property.setProperty(key, value);
			this.property.store(new FileWriter(fileProper), null);
			in.close();
		} catch (IOException e) {
			log.error("error en setKeyValueRepository {}",e);
		
		}

	}
	
	//si se pone en parametros map luego se puede recibir hasmap iterablemap...
	public void setKeyValueRepository(HashMap<String, String> mapa) {
		try {
			InputStream in = new FileInputStream(fileProper);
			this.property.load(in);
			mapa.forEach((k, v) -> this.property.setProperty(k.toString(), v.toString()));
			this.property.store(new FileWriter(fileProper), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("error en setKeyValueRepository {}",e);
			
		}
	}

	public boolean exitsProperty(String key) {
		try {
			InputStream in = new FileInputStream(fileProper);
			this.property.load(in);// lo casteamos y pasamos a optional
			if (this.property.get(key) != null)
				return true;
		} catch (IOException e) {
			System.out.print("Propiedad no existe");
		} // Optional.of(null) dará un NullPointer y Optional.ofNullable(null) dara un
			// Optional.empty.
		return false;
	}
}
