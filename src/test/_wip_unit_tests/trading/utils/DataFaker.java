package com.example.service.trading.utils;

import com.example.service.trading.infrastructure.adapters.api.models.user.SaveUserBodyDto;
import com.example.service.trading.infrastructure.adapters.api.models.user.UserDto;
import com.example.service.trading.infrastructure.adapters.persistence.models.UserData;
import com.example.service.trading.domain.user.User;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.time.LocalDateTime;
import java.util.Locale;

public class DataFaker {

    private static final Faker FAKER = new Faker();

    private static final FakeValuesService FAKE_VALUES_SERVICE = new FakeValuesService(Locale.CANADA, new RandomService());

    public static Integer fakeUserId() {
        return fakeUserIdAsInt();
    }

    public static int fakeUserIdAsInt() {
        return FAKER.number().numberBetween(1, 10000);
    }

    public static FullName fakeFullName() {
        return FullName.of(FAKER.name().firstName(), FAKER.name().nameWithMiddle(), FAKER.name().lastName());
    }

    public static Phone fakePhone() {
        return Phone.of(fakePhoneNumberAsString());
    }

    private static String fakePhoneNumberAsString() {
        return FAKE_VALUES_SERVICE.regexify("\\(\\+[1-9]\\d{1,2}\\) [0-9]{1,3}[0-9\\-]{6,9}[0-9]{1}");
    }

    public static User.UserBuilder fakeUserBuilder() {
        return User.builder()
                .id(DataFaker.fakeUserId())
                .fullName(fakeFullName())
                .phone(DataFaker.fakePhone());
    }

    public static User fakeUser() {
        return fakeUserBuilder()
                .build();
    }

    public static UserData.UserDataBuilder fakeUserDataBuilder() {
        return UserData.builder()
                .id(fakeUserIdAsInt())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .phone(fakePhoneNumberAsString())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now());
    }

    public static UserData fakeUserData() {
        return fakeUserDataBuilder()
                .build();
    }

    public static UserDto fakeUserDto() {
        return UserDto.builder()
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().lastName())
                .phone(fakePhoneNumberAsString())
                .build();
    }

    public static SaveUserBodyDto fakeSaveUserBodyDto() {
        return SaveUserBodyDto.of(
                FAKER.name().firstName(), FAKER.name().lastName(), fakePhoneNumberAsString());
    }

}
