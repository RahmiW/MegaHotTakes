package org.example.megahottakes.controller;


import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.services.HotTakeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/hotTake")
public class HotTakeController {
    private final HotTakeService hotTakeService;

    public HotTakeController(HotTakeService hotTakeService) {
        this.hotTakeService = hotTakeService;
    }

    // Create
    @PostMapping("/user/{id}")
    public HotTake createHotTakePost(@PathVariable Long id, @RequestBody HotTake hotTake) {
        return hotTakeService.createHotTake(id, hotTake.getContent());
    }
    // Read
    @GetMapping("/{id}")
    public HotTake getHotTake(@PathVariable Long id){
        return hotTakeService.getHotTake(id);
    }
    @GetMapping("/{id}/hottakes")
    public Set<HotTake> getHotTakeById(@PathVariable Long id){
        return hotTakeService.getHotTakesByUser(id);
    }
    @GetMapping("/feed")
    public List<HotTake> hotTakeFeed() {
        return hotTakeService.getHotTakeFeed();
    }
    @GetMapping("/search")
    public List<HotTake> searchHotTakeFeed(@RequestParam String keyword) {
        return hotTakeService.searchHotTakes(keyword);
    }
    @GetMapping("/{id}/heatscore")
    public Integer getHeatScore(@PathVariable Long id){
        return hotTakeService.getHeatScore(id);
    }
    // Update
    @PutMapping("/{id}")
    public HotTake updateHotTake(@PathVariable Long id, @RequestBody HotTake hotTake) {
        return hotTakeService.updateHotTake(id, hotTake.getContent());
    }
    @PatchMapping("/{hotTakeId}/like/{userId}")
    public int likeHotTake(@PathVariable Long hotTakeId, @PathVariable Long userId) {
        return hotTakeService.addToHeatScore(userId, hotTakeId);
    }
    // Delete
    @DeleteMapping("/{id}")
    public void deleteHotTake(@PathVariable Long id){
        hotTakeService.deleteHotTake(id);
    }
    @DeleteMapping("/{hotTakeId}/like/{userId}")
    public int unlikeHotTake(@PathVariable Long hotTakeId, @PathVariable Long userId) {
        return hotTakeService.decreaseHeatScore(userId, hotTakeId);
    }
}
