package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.RecordingDriver;
import edu.kis.powp.jobs2d.features.CommandsFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class SelectRunCurrentCommandOptionListener implements ActionListener {

    public SelectRunCurrentCommandOptionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DriverCommand command = CommandsFeature.getDriverCommandManager().getCurrentCommand();
        if (command == null) {
            return;
        }

        RecordingDriver rec = DriverFeature.getRecordingDriver();
        boolean previousState = rec.isRecordingEnabled();
        rec.setRecordingEnabled(false);
        try {
            command.execute(DriverFeature.getRecordingDriver());
        } finally {
            rec.setRecordingEnabled(previousState);
        }
    }
}
