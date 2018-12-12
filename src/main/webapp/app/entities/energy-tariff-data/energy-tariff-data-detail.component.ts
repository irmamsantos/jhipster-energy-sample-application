import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyTariffData } from 'app/shared/model/energy-tariff-data.model';

@Component({
    selector: 'jhi-energy-tariff-data-detail',
    templateUrl: './energy-tariff-data-detail.component.html'
})
export class EnergyTariffDataDetailComponent implements OnInit {
    energyTariffData: IEnergyTariffData;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyTariffData }) => {
            this.energyTariffData = energyTariffData;
        });
    }

    previousState() {
        window.history.back();
    }
}
