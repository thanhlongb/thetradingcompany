package controller;

import model.*;
import service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/receivingNote")
public class ReceivingNoteController {

    @Autowired
    private ReceivingNoteService receivingNoteService;

    public ReceivingNoteController(ReceivingNoteService receivingNoteService) {
        this.receivingNoteService = receivingNoteService;
    }

    // CREATE
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ReceivingNote createReceivingNote(@RequestBody ReceivingNote receivingNote){
        return receivingNoteService.saveReceivingNote(receivingNote);
    }

    @RequestMapping(path = "detail", method = RequestMethod.POST)
    public ReceivingNoteDetail createReceivingNoteDetail(@RequestBody ReceivingNoteDetail receivingNoteDetail){
        return receivingNoteService.saveReceivingNoteDetail(receivingNoteDetail);
    }

    // READ
    @RequestMapping(path = "{id}", method = RequestMethod.GET)
    public ReceivingNote getReceivingNote(@PathVariable int id) {
        return receivingNoteService.getReceivingNote(id);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<ReceivingNote> getAllReceivingNotes(@RequestParam(value = "page", required = false) Integer page,
                                                    @RequestParam(value = "limit", required = false) Integer limit,
                                                    @RequestParam(value = "from", required = false) Long from,
                                                    @RequestParam(value = "to", required = false) Long to) {
        return receivingNoteService.getAllReceivingNotes(page, limit, from, to);
    }

    @RequestMapping(path = "detail/{id}", method = RequestMethod.GET)
    public ReceivingNoteDetail getReceivingNoteDetail(@PathVariable int id) {
        return receivingNoteService.getReceivingNoteDetail(id);
    }

    @RequestMapping(path = "detail", method = RequestMethod.GET)
    public List<ReceivingNoteDetail> getAllReceivingNoteDetails(@RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "limit", required = false) Integer limit) {
        return receivingNoteService.getAllReceivingNoteDetails(page, limit);
    }

    // UPDATE
    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ReceivingNote updateReceivingNote(@RequestBody ReceivingNote receivingNote) {
        return receivingNoteService.updateReceivingNote(receivingNote);
    }

    @RequestMapping(path = "detail", method = RequestMethod.PUT)
    public ReceivingNoteDetail updateReceivingNoteDetail(@RequestBody ReceivingNoteDetail receivingNoteDetail) {
        return receivingNoteService.updateReceivingNoteDetail(receivingNoteDetail);
    }

    // DELETE
    @RequestMapping(path = "", method = RequestMethod.DELETE)
    public ReceivingNote deleteReceivingNote(@RequestBody ReceivingNote receivingNote) {
        return receivingNoteService.deleteReceivingNote(receivingNote);
    }

    @RequestMapping(path = "detail", method = RequestMethod.DELETE)
    public ReceivingNoteDetail deleteReceivingNoteDetail(@RequestBody ReceivingNoteDetail receivingNoteDetail) {
        return receivingNoteService.deleteReceivingNoteDetail(receivingNoteDetail);
    }
}
