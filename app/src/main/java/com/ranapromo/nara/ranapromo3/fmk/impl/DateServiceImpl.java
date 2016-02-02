package com.ranapromo.nara.ranapromo3.fmk.impl;

import com.ranapromo.nara.ranapromo3.comman.Util;
import com.ranapromo.nara.ranapromo3.fmk.IDateService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class DateServiceImpl implements IDateService {

	private String fileName;

	public DateServiceImpl(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Date getLastUpdateDate() {
		File myFile = new File(fileName);
		if (!myFile.exists()) return null;
		Date d = null;
		FileInputStream fos = null;
		ObjectInputStream oos = null;
		try {
			fos = new FileInputStream(fileName);
			oos = new ObjectInputStream(fos);
			d = (Date) oos.readObject();
			oos.close();
			fos.close();
		} catch (Exception e) {
			Util.logError("d'écriture da la derniére date de sauvegarde "+e.getMessage());
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return d;
	}

	@Override
	public void saveLastUpdateDate(Date date) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(date);
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out
					.println("Erreur d'�criture da la derni�re date de sauvegarde");
		} finally {
			try {
				if (oos != null)
					oos.close();
				if (fos != null)
					fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
