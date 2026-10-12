public class Base62Strategy implements KeyGenerationStrategy{
    private static final String BAsE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long OFFSET = 914_891_328L;

    @Override
    public String generateKey(long id) {
        id = id+OFFSET;
        StringBuilder sb = new StringBuilder();
        while(id > 0){
            sb.append(BAsE62_CHARS.charAt((int)(id%62)));
            id/=62;
        }
        return sb.reverse().toString();
    }
    
}
