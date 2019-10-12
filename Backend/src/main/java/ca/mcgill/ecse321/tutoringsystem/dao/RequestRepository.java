package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Request;

public interface RequestRepository extends CrudRepository<Request, Integer>{
  Request findRequestByRequestId(int requestId);
  List<Request> findRequestByDate(Date date);
}
