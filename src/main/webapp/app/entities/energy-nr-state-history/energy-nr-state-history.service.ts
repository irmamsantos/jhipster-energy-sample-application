import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyNRStateHistory } from 'app/shared/model/energy-nr-state-history.model';

type EntityResponseType = HttpResponse<IEnergyNRStateHistory>;
type EntityArrayResponseType = HttpResponse<IEnergyNRStateHistory[]>;

@Injectable({ providedIn: 'root' })
export class EnergyNRStateHistoryService {
    public resourceUrl = SERVER_API_URL + 'api/energy-nr-state-histories';

    constructor(private http: HttpClient) {}

    create(energyNRStateHistory: IEnergyNRStateHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(energyNRStateHistory);
        return this.http
            .post<IEnergyNRStateHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(energyNRStateHistory: IEnergyNRStateHistory): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(energyNRStateHistory);
        return this.http
            .put<IEnergyNRStateHistory>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEnergyNRStateHistory>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEnergyNRStateHistory[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(energyNRStateHistory: IEnergyNRStateHistory): IEnergyNRStateHistory {
        const copy: IEnergyNRStateHistory = Object.assign({}, energyNRStateHistory, {
            updateDate:
                energyNRStateHistory.updateDate != null && energyNRStateHistory.updateDate.isValid()
                    ? energyNRStateHistory.updateDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.updateDate = res.body.updateDate != null ? moment(res.body.updateDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((energyNRStateHistory: IEnergyNRStateHistory) => {
                energyNRStateHistory.updateDate = energyNRStateHistory.updateDate != null ? moment(energyNRStateHistory.updateDate) : null;
            });
        }
        return res;
    }
}
