package com.kalgookso.august.repository;

import com.kalgookso.august.entity.BoardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BoardTypeRepositoryTest {

    @Mock
    private BoardTypeRepository boardTypeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return BoardType when findById is called with existing id")
    void findByIdExistingId() {
        BoardType boardType = new BoardType();
        boardType.setId("test-id");
        when(boardTypeRepository.findById("test-id")).thenReturn(Optional.of(boardType));

        Optional<BoardType> result = boardTypeRepository.findById("test-id");

        assertTrue(result.isPresent());
        assertEquals(boardType, result.get());
    }

    @Test
    @DisplayName("Should return empty Optional when findById is called with non-existing id")
    void findByIdNonExistingId() {
        when(boardTypeRepository.findById("test-id")).thenReturn(Optional.empty());

        Optional<BoardType> result = boardTypeRepository.findById("test-id");

        assertFalse(result.isPresent());
    }

    @Test
    @DisplayName("Should save and return BoardType when save is called")
    void saveBoardType() {
        BoardType boardType = new BoardType();
        boardType.setId("test-id");
        when(boardTypeRepository.save(boardType)).thenReturn(boardType);

        BoardType result = boardTypeRepository.save(boardType);

        assertEquals(boardType, result);
    }

    @Test
    @DisplayName("Should call deleteById when deleteById is called with id")
    void deleteById() {
        doNothing().when(boardTypeRepository).deleteById("test-id");

        boardTypeRepository.deleteById("test-id");

        verify(boardTypeRepository, times(1)).deleteById("test-id");
    }
}