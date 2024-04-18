package de.ulrichcech.mbcc.server.business.basicdata.boundary.command;

import de.ulrichcech.mbcc.server.business.car.entity.CarType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CommandGetAllAvailableTypesTest {

    @Test
    void getAllCarTypes_sucess() {
        CommandGetAllAvailableTypes cut = new CommandGetAllAvailableTypes();

        final var typedQuery = preparePersistence(cut);
        Mockito.when(typedQuery.getResultList()).thenReturn(
                List.of(new CarType("Limousine", new BigDecimal("30000")))
        );
        final List<CarType> carTypeList = cut.execute();
        assertThat(carTypeList.isEmpty(), is(false));
        assertThat(carTypeList.size(), Is.is(1));
        assertThat(carTypeList.get(0), Is.is(new CarType("Limousine", new BigDecimal("30000"))));
    }

    @Test
    void getAllCarTypes_fail() {
        CommandGetAllAvailableTypes cut = new CommandGetAllAvailableTypes();

        final var typedQuery = preparePersistence(cut);

        // case: result is empty
        Mockito.when(typedQuery.getResultList()).thenReturn(Collections.emptyList());
        var carTypeList = cut.execute();
        assertThat(carTypeList.isEmpty(), is(true));

        // case: result is null
        Mockito.when(typedQuery.getResultList()).thenReturn(null);
        carTypeList = cut.execute();
        assertThat(carTypeList.isEmpty(), is(true));
    }


    @SuppressWarnings("unchecked")
    private TypedQuery<CarType> preparePersistence(CommandGetAllAvailableTypes cut) {
        final var mockedEntityManager = Mockito.mock(EntityManager.class);
        cut.setEntityManager(mockedEntityManager);
        final TypedQuery<CarType> typedQuery = (TypedQuery<CarType>) Mockito.mock(TypedQuery.class);
        Mockito.when(mockedEntityManager.createNamedQuery(CarType.FIND_ALL, CarType.class)).thenReturn(typedQuery);
        return typedQuery;
    }

}