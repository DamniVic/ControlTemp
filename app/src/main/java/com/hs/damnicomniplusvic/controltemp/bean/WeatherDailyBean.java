package com.hs.damnicomniplusvic.controltemp.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by DAMNICOMNIPLUSVIC on 2017/4/13.
 * (c) 2017 DAMNICOMNIPLUSVIC Inc,All Rights Reserved.
 */

public class WeatherDailyBean implements Parcelable{

    private List<ResultsBean> results;

    protected WeatherDailyBean(Parcel in) {
    }

    public static final Creator<WeatherDailyBean> CREATOR = new Creator<WeatherDailyBean>() {
        @Override
        public WeatherDailyBean createFromParcel(Parcel in) {
            return new WeatherDailyBean(in);
        }

        @Override
        public WeatherDailyBean[] newArray(int size) {
            return new WeatherDailyBean[size];
        }
    };

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class ResultsBean {
        /**
         * location : {"id":"WX4FBXXFKE4F","name":"北京","country":"CN","path":"北京,北京,中国","timezone":"Asia/Shanghai","timezone_offset":"+08:00"}
         * daily : [{"date":"2017-04-13","text_day":"阴","code_day":"9","text_night":"阴","code_night":"9","high":"21","low":"8","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"10","wind_scale":"2"},{"date":"2017-04-14","text_day":"多云","code_day":"4","text_night":"多云","code_night":"4","high":"26","low":"8","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"10","wind_scale":"2"},{"date":"2017-04-15","text_day":"晴","code_day":"0","text_night":"多云","code_night":"4","high":"29","low":"16","precip":"","wind_direction":"南","wind_direction_degree":"180","wind_speed":"10","wind_scale":"2"}]
         * last_update : 2017-04-13T08:00:00+08:00
         */

        private LocationBean location;
        private String last_update;
        private List<DailyBean> daily;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public List<DailyBean> getDaily() {
            return daily;
        }

        public void setDaily(List<DailyBean> daily) {
            this.daily = daily;
        }

        public static class LocationBean {
            /**
             * id : WX4FBXXFKE4F
             * name : 北京
             * country : CN
             * path : 北京,北京,中国
             * timezone : Asia/Shanghai
             * timezone_offset : +08:00
             */

            private String id;
            private String name;
            private String country;
            private String path;
            private String timezone;
            private String timezone_offset;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public String getTimezone_offset() {
                return timezone_offset;
            }

            public void setTimezone_offset(String timezone_offset) {
                this.timezone_offset = timezone_offset;
            }
        }

        public static class DailyBean implements Parcelable{
            /**
             * date : 2017-04-13
             * text_day : 阴
             * code_day : 9
             * text_night : 阴
             * code_night : 9
             * high : 21
             * low : 8
             * precip :
             * wind_direction : 南
             * wind_direction_degree : 180
             * wind_speed : 10
             * wind_scale : 2
             */

            private String date;
            private String text_day;
            private String code_day;
            private String text_night;
            private String code_night;
            private String high;
            private String low;
            private String precip;
            private String wind_direction;
            private String wind_direction_degree;
            private String wind_speed;
            private String wind_scale;

            protected DailyBean(Parcel in) {
                date = in.readString();
                text_day = in.readString();
                code_day = in.readString();
                text_night = in.readString();
                code_night = in.readString();
                high = in.readString();
                low = in.readString();
                precip = in.readString();
                wind_direction = in.readString();
                wind_direction_degree = in.readString();
                wind_speed = in.readString();
                wind_scale = in.readString();
            }

            public static final Creator<DailyBean> CREATOR = new Creator<DailyBean>() {
                @Override
                public DailyBean createFromParcel(Parcel in) {
                    return new DailyBean(in);
                }

                @Override
                public DailyBean[] newArray(int size) {
                    return new DailyBean[size];
                }
            };

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getText_day() {
                return text_day;
            }

            public void setText_day(String text_day) {
                this.text_day = text_day;
            }

            public String getCode_day() {
                return code_day;
            }

            public void setCode_day(String code_day) {
                this.code_day = code_day;
            }

            public String getText_night() {
                return text_night;
            }

            public void setText_night(String text_night) {
                this.text_night = text_night;
            }

            public String getCode_night() {
                return code_night;
            }

            public void setCode_night(String code_night) {
                this.code_night = code_night;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getPrecip() {
                return precip;
            }

            public void setPrecip(String precip) {
                this.precip = precip;
            }

            public String getWind_direction() {
                return wind_direction;
            }

            public void setWind_direction(String wind_direction) {
                this.wind_direction = wind_direction;
            }

            public String getWind_direction_degree() {
                return wind_direction_degree;
            }

            public void setWind_direction_degree(String wind_direction_degree) {
                this.wind_direction_degree = wind_direction_degree;
            }

            public String getWind_speed() {
                return wind_speed;
            }

            public void setWind_speed(String wind_speed) {
                this.wind_speed = wind_speed;
            }

            public String getWind_scale() {
                return wind_scale;
            }

            public void setWind_scale(String wind_scale) {
                this.wind_scale = wind_scale;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(date);
                dest.writeString(text_day);
                dest.writeString(code_day);
                dest.writeString(text_night);
                dest.writeString(code_night);
                dest.writeString(high);
                dest.writeString(low);
                dest.writeString(precip);
                dest.writeString(wind_direction);
                dest.writeString(wind_direction_degree);
                dest.writeString(wind_speed);
                dest.writeString(wind_scale);
            }
        }
    }
}
