package com.ranapromo.nara.ranapromo3.fmk;

import java.util.Date;

public interface IDateService {
  Date getLastUpdateDate();
  void saveLastUpdateDate(Date date);
}
