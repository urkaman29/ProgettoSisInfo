package com.sisinfo.Service;

import com.sisinfo.Entity.Employee;
import com.sisinfo.Entity.Event;
import com.sisinfo.Extra.LinkEmployeeEvent;
import com.sisinfo.Entity.Link;
import com.sisinfo.Repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private EventService eventService;

    @Transactional
    public ResponseEntity<?> createLink(Link link) {
        Employee employee = employeeService.findById(link.getEmployee().getId()).orElseThrow();
        Event event = eventService.findById(link.getEvent().getId()).orElseThrow();

        link.setEvent(event);
        link.setEmployee(employee);
        linkRepository.save(link);

        System.out.println(event);
        System.out.println(employee);
        System.out.println(link);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public List<Employee> findEmployeeByEvent(Event event) {
        List<LinkEmployeeEvent> linkEmployeeEvents = (List<LinkEmployeeEvent>) linkRepository.findByEvent(event);

        List<Employee> employees = new ArrayList<>();
        for (LinkEmployeeEvent li : linkEmployeeEvents) {
            employees.add(li.getEmployee());
        }
        return employees;
    }


    public List<Event> findEventByEmployee(Employee employee) {
        List<LinkEmployeeEvent> linkEmployeeEvents = (List<LinkEmployeeEvent>) linkRepository.findByEmployee(employee);

        List<Event> events = new ArrayList<>();
        for (LinkEmployeeEvent ev : linkEmployeeEvents) {
            events.add(ev.getEvent());
        }
        return events;
    }

    public Link getLinkedById(Long linkId) {
        return linkRepository.findById(linkId).orElse(null);
    }

    public Link updateLink(Long linkId, LinkEmployeeEvent linkDetails) {
        Link link = getLinkedById(linkId);

        return link;
    }
}

