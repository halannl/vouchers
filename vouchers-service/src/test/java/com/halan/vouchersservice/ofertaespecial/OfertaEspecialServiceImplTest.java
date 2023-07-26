package com.halan.vouchersservice.ofertaespecial;

import com.halan.vouchersmodel.ofertaespecial.OfertaEspecial;
import com.halan.vouchersmodel.ofertaespecial.OfertaEspecialRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfertaEspecialServiceImplTest {

    @InjectMocks
    private OfertaEspecialServiceImpl ofertaEspecialService;

    @Mock
    private OfertaEspecialRepository ofertaEspecialRepository;

    @Test
    void testGetOfertaByCodigoExistente() {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo1", "descricao 1", (short) 10);

        when(ofertaEspecialRepository.findById("codigo1")).thenReturn(Optional.of(ofertaEspecial));

        Optional<OfertaEspecial> resultado = ofertaEspecialService.get("codigo1");

        assertTrue(resultado.isPresent());
        assertEquals("codigo1", resultado.get().getCodigo());
        assertEquals("descricao 1", resultado.get().getDescricao());
        assertEquals((short) 10, resultado.get().getDescontoPercentual());

        verify(ofertaEspecialRepository, times(1)).findById("codigo1");
    }

    @Test
    void testGetOfertaByCodigoInexistente() {
        when(ofertaEspecialRepository.findById("codigo2")).thenReturn(Optional.empty());

        Optional<OfertaEspecial> resultado = ofertaEspecialService.get("codigo2");

        assertFalse(resultado.isPresent());

        verify(ofertaEspecialRepository, times(1)).findById("codigo2");
    }

    @Test
    void testSaveOfertaNova() {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo3", "descricao 3", (short) 30);

        when(ofertaEspecialRepository.existsById("codigo3")).thenReturn(true);

        boolean resultado = ofertaEspecialService.save(ofertaEspecial);

        assertTrue(resultado);

        verify(ofertaEspecialRepository, times(1)).existsById("codigo3");
        verify(ofertaEspecialRepository, never()).save(ofertaEspecial);
    }

    @Test
    void testSaveOfertaExistente() {
        OfertaEspecial ofertaEspecial = new OfertaEspecial("codigo4", "descricao 4", (short) 40);

        when(ofertaEspecialRepository.existsById("codigo4")).thenReturn(false);

        boolean resultado = ofertaEspecialService.save(ofertaEspecial);

        assertFalse(resultado);

        verify(ofertaEspecialRepository, times(1)).existsById("codigo4");
        verify(ofertaEspecialRepository, times(1)).save(ofertaEspecial);
    }
}
