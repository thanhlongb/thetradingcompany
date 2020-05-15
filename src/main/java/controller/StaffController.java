package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // CREATE
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Staff createStaff(@RequestBody Staff staff){
        return staffService.saveStaff(staff);
    }

    // READ
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public Staff getStaff(@PathVariable int id) {
        return staffService.getStaff(id);
    }

    @RequestMapping(path = "{id}/revenue", method = RequestMethod.GET)
    public String getStaffRevenue(@PathVariable int id,
                                     @RequestParam(value = "from", required = false) Long from,
                                     @RequestParam(value = "to", required = false) Long to) {
        return staffService.getRevenue(id, from, to);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Staff> getAllStaffs(@RequestParam(value = "page", required = false) Integer page,
                                    @RequestParam(value = "limit", required = false) Integer limit) {
        return staffService.getAllStaffs(page, limit);
    }

    @RequestMapping(path = "search", method = RequestMethod.GET)
    public List<Staff> searchStaffsByName(@RequestParam("name") String name){
        return staffService.searchStaffsByName(name);
    }

    // UPDATE
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public Staff updateStaff(@RequestBody Staff staff) {
        return staffService.updateStaff(staff);
    }

    // DELETE
    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public Staff deleteStaff(@RequestBody Staff staff) {
        return staffService.deleteStaff(staff);
    }
}
