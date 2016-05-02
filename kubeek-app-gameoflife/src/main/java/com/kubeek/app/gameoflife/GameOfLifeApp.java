package com.kubeek.app.gameoflife;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Random;

@Slf4j
@org.springframework.stereotype.Component
@Scope(value = "singleton")
public class GameOfLifeApp extends KApp {

    private KAppManager kAppManager;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private GameOfLifeParams gameOfLifeParams;
    private KScreenParam kScreenParam;
    private boolean[][] cells;
    private boolean[][] cellsn_2;
    private boolean[][] cellsn_3;
    private int sizex, sizey;
    private int generationValue = 0;

    @Autowired
    public GameOfLifeApp(KAppManager kAppManager, KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, GameOfLifeParams gameOfLifeParams, KScreenParam kScreenParam) {
        this.kAppManager = kAppManager;
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.gameOfLifeParams = gameOfLifeParams;
        this.kScreenParam = kScreenParam;
        this.sizex = kScreenParam.getWidth();
        this.sizey = kScreenParam.getHeight();
        this.setName("GameOfLife App");
    }

    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("GameOfLife app is loaded");
    }

    @Override
    public void execute() {

        final Random rand = new Random();
        cells = new boolean[sizex][sizey];
        cellsn_2=new boolean[sizex][sizey];
        cellsn_3=new boolean[sizex][sizey];
        for (int row = 0; row < sizex; row++) {
            cells[ row] = new boolean[sizey];
            for (int col = 0; col < sizey; col++) {
                cells[ row][ col] = (rand.nextInt(2) == 0);
                cellsn_2[ row][ col] = false;
                cellsn_3[ row][ col] = false;
            }
        }




        for(int i = 0; i < gameOfLifeParams.getGeneration(); i++){
            try {
                this.printGeneration();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.next();
            Utils.pause(gameOfLifeParams.getDelay());
        }

        stop();
    }

    @Override
    public void stop() {
        //clear();
    }

    public void clear() {
        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean printGeneration() throws InterruptedException {
        generationValue++;
        boolean retour = true;
        boolean cellReturn = true;
        for (int row = 0; row < sizex; row++) {
            for (int col = 0; col < sizey; col++) {
                //System.out.println("row,col:"+cells[row][col]);
                if (cells[row][col]) {

                    kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(row,col , gameOfLifeParams.getAppColor()));
                } else {
                    kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(row,col , new Color( 0, 0, 0)));
                }
                if (generationValue >=2) {
                    if (cellsn_2[row][col] != cells[row][col]) {
                        cellReturn = false;
                    }
                    if (cellsn_3[row][col] != cells[row][col]) {
                        cellReturn = false;
                    }
                }

            }

        }


        if ((generationValue % 2) == 0) {
            cellsn_2 = cells;

        }
        if ((generationValue % 3) == 0) {
            cellsn_3 = cells;

        }

        if (cellReturn) {
            retour = false;
        }



        return retour;
    }


    public void next() {


        boolean[][] tempCells = new boolean[sizex][sizey];


        for (int row = 0; row < sizex; row++) {
            for (int col = 0; col < sizey; col++) {
                int n = neighbours(row, col);

                if (n > 3 || n < 2) {
                    tempCells[row][col] = false;
                } else if (n == 3) {
                    tempCells[row][col] = true;
                } else {
                    tempCells[row][col] = cells[row][col];
                }
                //System.out.println("tempCells row,col:"+tempCells[row][col]);

            }

        }
        cells = tempCells;

    }

    public int neighbours(int row, int col) {
        int acc = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                try {
                    if (cells[i][j] == true && (i != row || j != col)) {
                        acc++;
                    }
                } catch (ArrayIndexOutOfBoundsException f) {
                    continue;
                }
            }
        }
        return acc;
    }
}
