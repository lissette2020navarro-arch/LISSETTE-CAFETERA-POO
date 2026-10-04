package com.mycompany.poo.cafetera;

// ==========================================
// 1. ABSTRACCIÓN
// ==========================================
// Clase abstracta que representa una cafetera general.
abstract class Cafetera {

    // ==========================================
    // 2. ENCAPSULAMIENTO
    // ==========================================
    private int nivelAguaMl;
    private int nivelGranosGramos;
    private String modelo;

    public Cafetera(String modelo, int nivelAguaMl, int nivelGranosGramos) {
        this.modelo = modelo;
        this.nivelAguaMl = nivelAguaMl;
        this.nivelGranosGramos = nivelGranosGramos;
    }

    public int getNivelAguaMl() {
        return nivelAguaMl;
    }

    public int getNivelGranosGramos() {
        return nivelGranosGramos;
    }

    public String getModelo() {
        return modelo;
    }

    // Método público para rellenar insumos
    public void rellenarInsumos(int agua, int granos) {

        if (agua > 0) {
            nivelAguaMl += agua;
        }

        if (granos > 0) {
            nivelGranosGramos += granos;
        }

        System.out.println(
                "[" + modelo + "] Recarga completada."
                + " Agua: " + nivelAguaMl + " ml,"
                + " Granos: " + nivelGranosGramos + " g."
        );
    }

    // Método protegido para consumir insumos
    protected boolean consumirInsumos(
            int aguaRequerida,
            int granosRequeridos
    ) {

        if (nivelAguaMl >= aguaRequerida
                && nivelGranosGramos >= granosRequeridos) {

            nivelAguaMl -= aguaRequerida;
            nivelGranosGramos -= granosRequeridos;

            return true;
        }

        System.out.println(
                "Insuficientes insumos en la "
                + modelo
                + " para preparar esta bebida."
        );

        return false;
    }

    // ==========================================
    // 4. POLIMORFISMO
    // ==========================================
    public abstract void prepararCafe();
}


// ==========================================
// 3. HERENCIA
// ==========================================
// CafeteraEspresso hereda de Cafetera
class CafeteraEspresso extends Cafetera {

    private boolean tieneVaporizadorLeche;

    public CafeteraEspresso(
            String modelo,
            int agua,
            int granos,
            boolean tieneVaporizador
    ) {

        super(modelo, agua, granos);

        this.tieneVaporizadorLeche = tieneVaporizador;
    }

    // ==========================================
    // 4. POLIMORFISMO
    // ==========================================
    @Override
    public void prepararCafe() {

        int aguaNecesaria = 50;
        int granosNecesarios = 18;

        if (consumirInsumos(
                aguaNecesaria,
                granosNecesarios
        )) {

            System.out.println(
                    "[" + getModelo()
                    + "] Extrayendo espresso concentrado"
                    + " a alta presión..."
            );

            if (tieneVaporizadorLeche) {

                System.out.println(
                        "Creando espuma de leche"
                        + " con el vaporizador..."
                );
            }

            System.out.println(
                    "Espresso listo para servir!"
            );

            System.out.println();
        }
    }
}


// ==========================================
// OTRA CLASE HIJA
// ==========================================
// CafeteraGoteo también hereda de Cafetera
class CafeteraGoteo extends Cafetera {

    private int capacidadJarraTazas;

    public CafeteraGoteo(
            String modelo,
            int agua,
            int granos,
            int capacidadJarraTazas
    ) {

        super(modelo, agua, granos);

        this.capacidadJarraTazas =
                capacidadJarraTazas;
    }

    @Override
    public void prepararCafe() {

        int aguaNecesaria = 200;
        int granosNecesarios = 15;

        if (consumirInsumos(
                aguaNecesaria,
                granosNecesarios
        )) {

            System.out.println(
                    "[" + getModelo()
                    + "] Calentando agua"
                    + " y goteando sobre el filtro..."
            );

            System.out.println(
                    "Llenando jarra con capacidad para "
                    + capacidadJarraTazas
                    + " tazas."
            );

            System.out.println(
                    "Cafe de filtro listo!"
            );

            System.out.println();
        }
    }
}


// ==========================================
// CLASE PRINCIPAL
// ==========================================
public class Main {

    public static void main(String[] args) {

        System.out.println(
                "=== DEMOSTRACION DE LOS 4 PILARES DE LA POO ==="
        );

        System.out.println();

        // ==========================================
        // CREACIÓN DE OBJETOS
        // ==========================================
        Cafetera miEspresso =
                new CafeteraEspresso(
                        "Oster Barista",
                        150,
                        40,
                        true
                );

        Cafetera miGoteo =
                new CafeteraGoteo(
                        "Black+Decker Classic",
                        500,
                        50,
                        10
                );

        // ==========================================
        // POLIMORFISMO
        // ==========================================
        Cafetera[] misCafeteras = {
            miEspresso,
            miGoteo
        };

        System.out.println(
                "--- PREPARANDO CAFES ---"
        );

        for (Cafetera cafetera : misCafeteras) {
            cafetera.prepararCafe();
        }

        // ==========================================
        // ENCAPSULAMIENTO
        // ==========================================
        System.out.println(
                "--- PREPARANDO OTRO ESPRESSO ---"
        );

        miEspresso.prepararCafe();

        System.out.println(
                "--- INTENTANDO PREPARAR UN TERCER ESPRESSO ---"
        );

        miEspresso.prepararCafe();

        // ==========================================
        // RELLENAR INSUMOS
        // ==========================================
        System.out.println(
                "--- RECARGANDO LA CAFETERA ---"
        );

        miEspresso.rellenarInsumos(
                200,
                50
        );

        System.out.println();

        System.out.println(
                "--- PREPARANDO CAFE DESPUES DE RECARGAR ---"
        );

        miEspresso.prepararCafe();

        // ==========================================
        // ESTADO FINAL
        // ==========================================
        System.out.println(
                "--- ESTADO FINAL ---"
        );

        System.out.println(
                "Agua restante: "
                + miEspresso.getNivelAguaMl()
                + " ml"
        );

        System.out.println(
                "Granos restantes: "
                + miEspresso.getNivelGranosGramos()
                + " g"
        );
    }
}