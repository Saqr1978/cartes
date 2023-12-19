package test.java;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.java.entities.Aventurier;
import main.java.entities.Carte;
import main.java.entities.Montagne;
import main.java.entities.Tresor;
import main.java.mappers.CarteMapper;

public class CarteMapperTest {

  private String testFilesPath = CarteMapper.class.getResource("cartes/").getPath();

  @Test
  void test_mapsWell() {
    Carte carte = CarteMapper.readFile(new File(this.testFilesPath + "test_mapper.txt"));
    List<Montagne> montagnes = carte.getMontagnes();
    List<Tresor> tresors = carte.getTresors();
    List<Aventurier> aventuriers = carte.getAventuriers();

    Montagne montagneUn = new Montagne()
        .position(List.of(2, 1));
    Montagne montagneDeux = new Montagne()
        .position(List.of(2, 2));

    Tresor tresor = new Tresor()
        .position(List.of(1, 1))
        .nombre(1);

    Aventurier aventurier = new Aventurier()
        .absciss(2)
        .ordinate(1)
        .nom("Laurent")
        .orientation(Aventurier.Orientation.O)
        .sequenceMouvements("AGGDAAD")
        .ordreApparition(0)
        .nombreTresorsRamasses(0);

    int[] expectedSize = { 4, 4 };
    assertArrayEquals(expectedSize, carte.getSize());
    assertEquals(2, montagnes.size());
    assertEquals(1, tresors.size());
    assertEquals(1, aventuriers.size());
    //assertThat(aventurier).isEqualTo(aventuriers.get(0)).usingRecursiveComparison();
    //assertThat(montagnes).isEqualTo(List.of(montagneUn, montagneDeux));

  }

}
