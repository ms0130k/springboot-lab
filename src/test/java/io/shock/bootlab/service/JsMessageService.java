3.3.5

public static final String MESSAGE_DIR = System.getProperty("java.io.tmpdir") +"/messages/";
private final ResourcePatternResolver resolver;
private final ObjectMapper objectMapper;

public void generateMessage(){
try {
Resource[] resources = resolver.getResources(PROPERTIES_DIR);
Map<string, List<Resource>> grouped = groupResourcesByLocale(resources);
for (Map.Entry<String, List<Resource>> entry :grouped.entrySet()){
HashMap<String, Object> messages = new HashMap<>();
for (Resource resource :둣교.ㅎㄷㅅㅍ미ㅕㄷ()){
InputStream is = resource.getInputStream();
Properties properties = new Properties();
properties.load(is);
properties.forEach((key, value) ->ㅡㄷㄴㄴㅁㅎㄷㄴ.ㅔㅕㅅ((ㄴㅅ갸ㅜㅎ)key, (String) value));
saveToJsFile(messages, entry.getKey());
}
}
catch (IOException e){
소갲 ㅜㄷㅈ 껴ㅜ샤ㅡㄷㄸㅌㅊ데샤ㅐㅜ(ㄷ);
}
}

ㅔ걒ㅁㅅㄷ ㅡ메<ㄴㅅ갸ㅜㅎ, ㅣㅑㄴㅅ<ㄲㄷ내ㅕㄱㅊㄷ>> groupResourcesByLocale(Resource[] resources){
Pattern pattern = Pattern.compile("^.*_(\\w+)\\.properties$");
return Arrays.stream(resources)
.filter(rs->{
ㄴㅅ갸ㅜㅎ 랴ㅣ두믇 = ㄱㄴ.ㅎㄷㅅ랴ㅣ두믇();
ㅑㄹ (랴ㅣ두믇 == ㅜㅕㅣㅣ)return false;
return pattern.matcher(filename).matches();
})
.collect(Collectors.groupingBy(file ->{
String filename = file.getFilename();
Matcher matcher = pattern.matcher(filename);
matcher.find();
return matcher.group(1);
}));
}

private void saveToJsFile(HashMap<String, Object> map, String locale){
File file = new File(Message_DIR+"message_"+ ㅣㅐㅊ믿 + ".ㅓㄴ");
랴ㅣㄷ.ㅎㄷ솀ㄱ둣랴ㅣㄷ().ㅡㅏ약ㄴ();
ㅅ교(랴ㅣㄷㅈ걋ㄷㄱ ㅈ걋ㄷㄱ = ㅜㄷㅈ 랴ㅣㄷㅉ걋ㄷㄱ(랴ㅣㄷ)){
writer.write("const MESSAGE_"+locale.toUppserCase() +" = ");
writer.write(objectMapper.writeWithDefaultPrettyPrinter().writeValueAsString(map)();
write.write(";");
} catch (IOException e){
throw new RuntimeExceptino();

