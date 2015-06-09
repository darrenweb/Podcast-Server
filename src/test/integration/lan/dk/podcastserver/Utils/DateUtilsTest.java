package lan.dk.podcastserver.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kevin on 27/09/2014.
 */
public class DateUtilsTest {

    private final Logger logger = LoggerFactory.getLogger(DateUtilsTest.class);
/*
    @Test
    public void should_get_the_right_localdatetime_from_upload_format () {
        ZonedDateTime zonedDateTime = PodcastBusiness.fromFolder("2014-12-21");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(0);
        assertThat(zonedDateTime.getMinute()).isEqualTo(0);
        assertThat(zonedDateTime.getSecond()).isEqualTo(0);
    }
    
    @Test
    public void should_parse_a_RFC822_date () {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse("Sun, 21 Dec 2014 11:05:30 GMT", DateTimeFormatter.RFC_1123_DATE_TIME);

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
        assertThat(zonedDateTime.getSecond()).isEqualTo(30);

    }

    @Test
    public void should_parse_a_canalplus_date () {
        ZonedDateTime zonedDateTime = CanalPlusUpdater.fromCanalPlus("21/12/2014", "11:05:30");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
        assertThat(zonedDateTime.getSecond()).isEqualTo(30);
    }

    @Test
    public void should_parse_youtube_date () {
        ZonedDateTime zonedDateTime = YoutubeUpdater.fromYoutube("2014-12-21T11:05:30.000Z");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
        assertThat(zonedDateTime.getSecond()).isEqualTo(30);
    }

    @Test
    public void should_output_RFC2822_date () {
        String date = "Sun, 21 Dec 2014 11:05:30 GMT";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, DateTimeFormatter.RFC_1123_DATE_TIME);
        assertThat(date).isEqualTo(zonedDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME));
    }

    @Test
    public void should_parse_beinsport_date () {
        ZonedDateTime zonedDateTime = BeInSportUpdater.fromBeInSport("Dec 21 2014, 11:05");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
    }

    @Test
    public void should_parse_jeuxvideofr_date () {
        ZonedDateTime zonedDateTime = JeuxVideoFRUpdater.fromJeuxVideoFr("21/12/2014");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
    }

    @Test
    public void should_parse_parleys_date () {
        ZonedDateTime zonedDateTime = ParleysUpdater.fromParleys("Sun Dec 21 11:05:30 UTC 2014");

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
        assertThat(zonedDateTime.getSecond()).isEqualTo(30);
    }

    @Test
    public void should_parse_pluzz_date () {
        ZonedDateTime zonedDateTime = PluzzUpdater.fromPluzz(1419156330L);

        assertThat(zonedDateTime.getYear()).isEqualTo(2014);
        assertThat(zonedDateTime.getMonthValue()).isEqualTo(12);
        assertThat(zonedDateTime.getDayOfMonth()).isEqualTo(21);
        assertThat(zonedDateTime.getHour()).isEqualTo(11);
        assertThat(zonedDateTime.getMinute()).isEqualTo(5);
        assertThat(zonedDateTime.getSecond()).isEqualTo(30);
    }*/
}