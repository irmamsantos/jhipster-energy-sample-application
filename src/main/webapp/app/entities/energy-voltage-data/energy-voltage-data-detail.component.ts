import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyVoltageData } from 'app/shared/model/energy-voltage-data.model';

@Component({
    selector: 'jhi-energy-voltage-data-detail',
    templateUrl: './energy-voltage-data-detail.component.html'
})
export class EnergyVoltageDataDetailComponent implements OnInit {
    energyVoltageData: IEnergyVoltageData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyVoltageData }) => {
            this.energyVoltageData = energyVoltageData;
        });
    }

    previousState() {
        window.history.back();
    }
}
