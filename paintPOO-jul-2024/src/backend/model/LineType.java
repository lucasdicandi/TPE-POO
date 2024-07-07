package backend.model;

public enum LineType {
    NORMAL(0){
        @Override
        public String toString(){
            return "Normal";
        }
    }, PUNTEADO(10){
        @Override
        public String toString(){
            return "Punteado";
        }
    }, PUNTEADO_COMPLEJO(30, 10, 15, 10){
        @Override
        public String toString(){
            return "P.Complejo";
        }
    };

    private final double[] dashes;

    LineType(double... dashes) {
        this.dashes = dashes;
    }

    public double[] getDashes() {
        return dashes;
    }

}
