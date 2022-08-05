package nextstep.subway.documentation;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.applicaion.PathService;
import nextstep.subway.applicaion.dto.PathResponse;
import nextstep.subway.applicaion.dto.StationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static nextstep.subway.acceptance.PathSteps.경로_조회;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class PathDocumentation extends Documentation {

    @MockBean
    private PathService pathService;

    @Test
    void path() {
        // given
        var 강남역 = new StationResponse(1L, "강남역");
        var 역삼역 = new StationResponse(2L, "역삼역");
        var pathResponse = new PathResponse(List.of(강남역, 역삼역), 10, 5);

        // when
        when(pathService.findPath(anyLong(), anyLong())).thenReturn(pathResponse);

        // then
        경로_조회(spec, 강남역.getId(), 역삼역.getId());
    }
}
