package ca.mcgill.ecse321.tutoringsystem.dto;

public class WageDto {
  private Integer wage;
  private Integer wageId;
  
  public WageDto() {
    
  }
  
  public WageDto(Integer wageId) {
    this(null, wageId);
  }
  
  public WageDto(Integer wage, Integer wageId) {
    this.wage = wage;
    this.wageId = wageId;
  }

  public Integer getWage() {
    return wage;
  }

  public void setWage(Integer wage) {
    this.wage = wage;
  }

  public Integer getWageId() {
    return wageId;
  }

  public void setWageId(Integer wageId) {
    this.wageId = wageId;
  }
  
}
