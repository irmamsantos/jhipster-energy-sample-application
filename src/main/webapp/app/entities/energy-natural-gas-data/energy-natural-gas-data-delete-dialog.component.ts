import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnergyNaturalGasData } from 'app/shared/model/energy-natural-gas-data.model';
import { EnergyNaturalGasDataService } from './energy-natural-gas-data.service';

@Component({
    selector: 'jhi-energy-natural-gas-data-delete-dialog',
    templateUrl: './energy-natural-gas-data-delete-dialog.component.html'
})
export class EnergyNaturalGasDataDeleteDialogComponent {
    energyNaturalGasData: IEnergyNaturalGasData;

    constructor(
        private energyNaturalGasDataService: EnergyNaturalGasDataService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.energyNaturalGasDataService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'energyNaturalGasDataListModification',
                content: 'Deleted an energyNaturalGasData'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-energy-natural-gas-data-delete-popup',
    template: ''
})
export class EnergyNaturalGasDataDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ energyNaturalGasData }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EnergyNaturalGasDataDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.energyNaturalGasData = energyNaturalGasData;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
