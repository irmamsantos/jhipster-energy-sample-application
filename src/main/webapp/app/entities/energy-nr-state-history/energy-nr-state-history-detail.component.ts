import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';

@Component({
    selector: 'jhi-energy-nr-state-history-detail',
    templateUrl: './energy-nr-state-history-detail.component.html'
})
export class EnergyNRStateHistoryDetailComponent implements OnInit {
    energyNRStateHistory: IEnergyNRStateHistory;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNRStateHistory }) => {
            this.energyNRStateHistory = energyNRStateHistory;
        });
    }

    previousState() {
        window.history.back();
    }
}
