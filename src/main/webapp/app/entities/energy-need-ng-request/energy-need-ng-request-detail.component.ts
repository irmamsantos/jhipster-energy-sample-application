import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';

@Component({
    selector: 'jhi-energy-need-ng-request-detail',
    templateUrl: './energy-need-ng-request-detail.component.html'
})
export class EnergyNeedNGRequestDetailComponent implements OnInit {
    energyNeedNGRequest: IEnergyNeedNGRequest;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNeedNGRequest }) => {
            this.energyNeedNGRequest = energyNeedNGRequest;
        });
    }

    previousState() {
        window.history.back();
    }
}
