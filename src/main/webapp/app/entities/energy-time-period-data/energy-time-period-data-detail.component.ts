import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyTimePeriodData } from 'app/shared/model/energy-time-period-data.model';

@Component({
    selector: 'jhi-energy-time-period-data-detail',
    templateUrl: './energy-time-period-data-detail.component.html'
})
export class EnergyTimePeriodDataDetailComponent implements OnInit {
    energyTimePeriodData: IEnergyTimePeriodData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyTimePeriodData }) => {
            this.energyTimePeriodData = energyTimePeriodData;
        });
    }

    previousState() {
        window.history.back();
    }
}
