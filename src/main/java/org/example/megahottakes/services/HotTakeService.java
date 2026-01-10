package org.example.megahottakes.services;

import org.example.megahottakes.entities.HotTake;
import org.example.megahottakes.entities.User;
import org.example.megahottakes.entities.Comment;
import org.example.megahottakes.repositories.CommentRepository;
import org.example.megahottakes.repositories.HotTakeRepository;
import org.example.megahottakes.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


// Layout:
// CRUD (Create Post and save to DB)
// Add comment

@Service
public class HotTakeService {
    private HotTakeRepository hotTakeRepository;
    private UserRepository userRepository;

    public HotTakeService(HotTakeRepository hotTakeRepository, UserRepository userRepository) {
        this.hotTakeRepository = hotTakeRepository;
        this.userRepository = userRepository;
    }

    // Create
    public HotTake createHotTake(Long id, String contentOfHotTake) {
        User authorOfHotTake = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The User was not Found")); // grab id of User
        if (contentOfHotTake == null || contentOfHotTake.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }
        HotTake hotTake = new HotTake();
        hotTake.setContent(contentOfHotTake);
        hotTake.setAuthor(authorOfHotTake);
        return hotTakeRepository.save(hotTake);
    }

    // Read
    public HotTake getHotTake(Long hotTakeId) {
        return hotTakeRepository.findById(hotTakeId)
                .orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
    }

    public List<HotTake> getHotTakeFeed() {
        LocalDateTime since = LocalDateTime.now().minusHours(48);
        return hotTakeRepository.findByCreationDateAfterOrderByLikedByUsersDesc(since);
    }

    public Set<HotTake> getHotTakesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        return user.getHotTakes();
    }

    public List<HotTake> searchHotTakes(String keyword) {
        return hotTakeRepository.findByContentContainingIgnoreCase(keyword);
    }

    // Update
    public HotTake updateHotTake(Long hotTakeId, String newContent) {
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        hotTake.setContent(newContent);
        return hotTakeRepository.save(hotTake);
    }

    // Delete
    public void deleteHotTake(Long hotTakeId) {
        hotTakeRepository.deleteById(hotTakeId);
    }
    // Like HotTake logic
    public Integer getHeatScore(Long hotTakeId) {
        HotTake hotTake = getHotTake(hotTakeId);
        return hotTake.getHeatScore();
    }
    public Integer addToHeatScore(Long userId, Long hotTakeId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        if (!user.getLikedHotTakes().contains(hotTake)) {
            user.getLikedHotTakes().add(hotTake);
            hotTake.setHeatScore(hotTake.getHeatScore() + 1);
            userRepository.save(user);
            hotTakeRepository.save(hotTake);
        }
        return hotTake.getHeatScore();
    }
    public Integer decreaseHeatScore(Long userId, Long hotTakeId){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("The User was not Found"));
        HotTake hotTake = hotTakeRepository.findById(hotTakeId).orElseThrow(() -> new IllegalArgumentException("The HotTake was not found"));
        if (user.getLikedHotTakes().contains(hotTake)) {
            user.getLikedHotTakes().remove(hotTake);
            hotTake.setHeatScore(hotTake.getHeatScore() - 1);
            userRepository.save(user);
            hotTakeRepository.save(hotTake);
        }
        return hotTake.getHeatScore();
    }
}
