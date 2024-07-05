package backend.model;



public enum ShadowType {
    NONE(0){
        @Override
        public String toString(){
            return "None";
        }
    },
    SIMPLE(10){
        @Override
        public String toString(){
            return "Simple";
        }
    },
    COLORED(10){
        @Override
        public String toString(){
            return "Coloreada";
        }
    },
    SIMPLE_INVERSE(-10){
        @Override
        public String toString(){
            return "Simple Inversa";
        }
    },
    COLORED_INVERSE(-10){
        @Override
        public String toString(){
            return "Coloreada Inversa";
        }
    };

    private final double offset;

    ShadowType(double offset){
        this.offset = offset;
    }

    public double getOffset() {
        return offset;
    }
}
