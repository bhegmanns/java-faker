package com.github.javafaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Name {
	
	private static final String TYPE_PREFIX = "name";
	
	private static final String NAME = "name";
	private static final String NAME_WITH_MIDDLE = "name_with_middle";
	private static final String FIRST_NAME = "first_name";
	private static final String LAST_NAME = "last_name";
	private static final String FIRT_MALE_NAME = "first_male_name";
	private static final String FIRST_FEMALE_NAME = "first_female_name";
	private static final String PREFIX = "prefix";
	private static final String SUFFIX = "suffix";
	private static final String TITLE_DESCRIPTOR = "title.descriptor";
	private static final String TITLE_LEVEL = "title.level";
	private static final String TITLE_JOB = "title.job";
    
    private final Faker faker;

    /**
     * Internal constructor, not to be used by clients.  Instances of {@link Name} should be accessed via 
     * {@link Faker#name()}.
     */
    Name(Faker faker) {
        this.faker = faker;
    }
    
    private String resolve(String ...keys){
    	List<String> keyList = new ArrayList<String>(keys.length + 1);
    	keyList.add(TYPE_PREFIX);
    	keyList.addAll(Arrays.asList(keys));
    	return faker.fakeValuesService().resolve(this, faker, keyList.toArray(new String[keyList.size()]));
    }

    /**
     * <p>
     *      A multipart name composed of an optional prefix, a firstname and a lastname
     *      or other possible variances based on locale.  Examples:
     *      <ul>
     *          <li>James Jones Jr.</li>
     *          <li>Julie Johnson</li>
     *      </ul>
     * </p>
     * @return a random name with given and family names and an optional suffix.
     */
    public String name() {
    	return resolve(NAME);
//        return faker.fakeValuesService().resolve("name.name", this, faker);
    }

    /**
     * <p>
     *      A multipart name composed of an optional prefix, a given and family name,
     *      another 'firstname' for the middle name and an optional suffix such as Jr. 
     *      Examples:
     *      <ul>
     *          <li>Mrs. Ella Geraldine Fitzgerald</li>
     *          <li>Jason Tom Sawyer Jr.</li>
     *          <li>Helen Jessica Troy</li>
     *      </ul>
     * </p>
     * @return a random name with a middle name component with optional prefix and suffix
     */
    public String nameWithMiddle() {
    	return resolve(NAME_WITH_MIDDLE);
//        return faker.fakeValuesService().resolve("name.name_with_middle", this, faker);
    }

    /**
     * <p>Returns the same value as {@link #name()}</p>
     * @see Name#name() 
     */
    public String fullName() {
        return name();
    }

    /**
     * <p>Returns a random 'given' name such as Aaliyah, Aaron, Abagail or Abbey</p>
     * @return a 'given' name such as Aaliyah, Aaron, Abagail or Abbey
     */
    public String firstName() {
    	return resolve(FIRST_NAME);
//        return faker.fakeValuesService().resolve("name.first_name", this, faker);
    }
    
    /**
     * Returns a random female name, fallback a normal name from {@link #firstName()}.
     * 
     * 
     * @return a random female name
     */
    public String femaleName(){
    	return faker.fakeValuesService().resolve("name.first_female_name", "name.first_name", this, faker);
    }
    
    /**
     * Returns a random male name, fallback a normal name from {@link #firstName()}.
     * 
     * @return a random male name
     */
    public String maleName(){
    	return faker.fakeValuesService().resolve("name.first_male_name", "name.first_name", this, faker);
    }

    /**
     * <p>Returns a random last name such as Smith, Jones or Baldwin</p>
     * @return a random last name such as Smith, Jones or Baldwin
     */
    public String lastName() {
    	return resolve(LAST_NAME);
//        return faker.fakeValuesService().resolve("name.last_name", this, faker);
    }

    /**
     * <p>Returns a name prefix such as Mr., Mrs., Ms., Miss, or Dr.</p>
     * @return a name prefix such as Mr., Mrs., Ms., Miss, or Dr.
     */
    public String prefix() {
    	return resolve(PREFIX);
//        return faker.fakeValuesService().resolve("name.prefix", this, faker);
    }

    /**
     * <p>Returns a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM</p>
     * @return a name suffix such as Jr., Sr., I, II, III, IV, V, MD, DDS, PhD or DVM
     */
    public String suffix() {
    	return resolve(SUFFIX);
//        return faker.fakeValuesService().resolve("name.suffix", this, faker);
    }

    /**
     * <p>
     *     A three part title composed of a descriptor level and job.  Some examples are :
     *     <ul>
     *         <li>(template) {descriptor} {level} {job}</li>
     *         <li>Lead Solutions Specialist</li>
     *         <li>National Marketing Manager</li>
     *         <li>Central Response Liaison</li>
     *     </ul>
     * </p>
     * @return a random three part job title
     */
    public String title() {
        return StringUtils.join(new String[] {
        		resolve(TITLE_DESCRIPTOR), resolve(TITLE_LEVEL), resolve(TITLE_JOB)},
//            faker.fakeValuesService().resolve("name.title.descriptor", this, faker), 
//            faker.fakeValuesService().resolve("name.title.level", this, faker), 
//            faker.fakeValuesService().resolve("name.title.job", this, faker) }, 
        		" ");
    }

    /**
     * <p>
     *     A lowercase username composed of the first_name and last_name joined with a '.'. Some examples are:
     *     <ul>
     *         <li>(template) {@link #firstName()}.{@link #lastName()}</li>
     *         <li>jim.jones</li>
     *         <li>jason.leigh</li>
     *         <li>tracy.jordan</li>
     *     </ul>
     * </p>
     * @return a random two part user name.
     * @see Name#firstName() 
     * @see Name#lastName()
     */
    public String username() {
        return StringUtils.join(new String[]{
                firstName().replaceAll("'", "").toLowerCase(),
                ".",
                lastName().replaceAll("'", "").toLowerCase()}
        );
    }
}
