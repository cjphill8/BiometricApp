package asu.capstone.biometricapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "biometricapp-mobilehub-927101236-Personal_Info")

public class PersonalInfoDO {
    private String _userId;
    private String _age;
    private String _firstName;
    private Boolean _gender;
    private Double _height;
    private String _lastName;
    private Double _weight;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "age")
    public String getAge() {
        return _age;
    }

    public void setAge(final String _age) {
        this._age = _age;
    }
    @DynamoDBAttribute(attributeName = "firstName")
    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(final String _firstName) {
        this._firstName = _firstName;
    }
    @DynamoDBAttribute(attributeName = "gender")
    public Boolean getGender() {
        return _gender;
    }

    public void setGender(final Boolean _gender) {
        this._gender = _gender;
    }
    @DynamoDBAttribute(attributeName = "height")
    public Double getHeight() {
        return _height;
    }

    public void setHeight(final Double _height) {
        this._height = _height;
    }
    @DynamoDBAttribute(attributeName = "lastName")
    public String getLastName() {
        return _lastName;
    }

    public void setLastName(final String _lastName) {
        this._lastName = _lastName;
    }
    @DynamoDBAttribute(attributeName = "weight")
    public Double getWeight() {
        return _weight;
    }

    public void setWeight(final Double _weight) {
        this._weight = _weight;
    }

}
