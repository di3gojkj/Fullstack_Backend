package com.diego.ms_productos.exception;


public class StockInsuficienteException extends RuntimeException {
    private final Long productoId;
    private final int stockActual;
    private final int cantidadSolicitada;

    public StockInsuficienteException(Long productoId, int cantidadSolicitada, int stockActual){
        super(String.format(
            "stock insuficiente para el producto ID %d. Stock actual: %d, cantidad solicitada: %d", productoId, stockActual, cantidadSolicitada
        ));
        this.productoId = productoId;
        this.stockActual = stockActual;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public long getProductoId(){return productoId;}
    public int getStockActual(){return stockActual;}
    public int getCantidadSolicitada(){return cantidadSolicitada;}





}
