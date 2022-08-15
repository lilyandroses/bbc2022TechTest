package io.testapi.cucumber.stepsdefinitions;

import io.testapi.bbcinfo.BbcSteps;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.Matchers.lessThan;

public class bbcMusicStepdefs {
    @Steps
    BbcSteps steps;
    static ValidatableResponse response;


    @When("^User sends a GET request to the media endpoints$")
    public void userSendsAGETRequestToTheMediaEndpoints()  {
        response = steps.getAllMediaTracks();
    }

    @Then("^then user must get back response with status code (\\d+)$")
    public void thenUserMustGetBackResponseWithStatusCode(int arg0) {
        response.statusCode(200);
    }

    @Then("^Response time should be below (\\d+) milliseconds$")
    public void responseTimeShouldBeBelowMilliseconds(int arg0) {
        response.time(lessThan(1000L), TimeUnit.MILLISECONDS);
    }

    @Then("^Verify that the id \"([^\"]*)\" field is never null or Empty for all the items present in the list$")
    public void verifyThatTheIdFieldIsNeverNullOrEmptyForAllTheItemsPresentInTheList(String arg0) throws Throwable {

        List<String> ids = response.extract().path("data.id");
        boolean isAnyIdNullOrEmpty = false;
        for (String id : ids) {

            if (id == null || id.isEmpty()) {
                isAnyIdNullOrEmpty = true;
            }
            Assert.assertEquals("ID key value is null or empty",false, isAnyIdNullOrEmpty);
        }
    }
    @Then("^the segment type \"([^\"]*)\" field for every track is music \"([^\"]*)\"$")
    public void theSegmentTypeFieldForEveryTrackIsMusic(String arg0, String arg1) throws Throwable {

        List<String> segmentTypesList = response.extract().path("data.segment_type");
        for (String segmentType : segmentTypesList) {
            Assert.assertEquals("music", segmentType);
        }
    }
    @Then("^Verify that the primary \"([^\"]*)\" field in the  title list \"([^\"]*)\" is never  null or empty for any track$")
    public void verifyThatThePrimaryFieldInTheTitleListIsNeverNullOrEmptyForAnyTrack(String arg0, String arg1) throws Throwable {

        List<String> primaryFields = response.extract().path("data.title_list.primary");
        boolean isAnyPriFieldNullOrEmpty = false;
        for (String primaryField : primaryFields) {

            if (primaryField == null ||primaryField.isEmpty()) {
                isAnyPriFieldNullOrEmpty = true;
            }
            Assert.assertEquals("primary key value is null or empty",false, isAnyPriFieldNullOrEmpty);
        }
    }

    @Then("^Verify that only one track in the list has \"([^\"]*)\" field in \"([^\"]*)\" as true$")
    public void verifyThatOnlyOneTrackInTheListHasFieldInAsTrue(String arg0, String arg1) throws Throwable {
        List<Boolean> valuesOfNowPlaying = response.extract().path("data.offset.now_playing");

        int count = 0;
        for (Boolean nowPlaying : valuesOfNowPlaying) {
            if (nowPlaying = true) {
                count = count + 1;
            }
        }
        Assert.assertEquals("Now_paying has true value more than one ",1, count);
    }

    @Then("^Verify the \"([^\"]*)\" value in the response headers$")
    public void verifyTheValueInTheResponseHeaders(String arg0) throws Throwable {
        response.header("date", "Fri, 21 May");
    }



}

