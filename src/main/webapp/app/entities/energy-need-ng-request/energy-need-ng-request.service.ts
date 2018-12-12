import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyNeedNGRequest } from 'app/shared/model/energy-need-ng-request.model';

type EntityResponseType = HttpResponse<IEnergyNeedNGRequest>;
type EntityArrayResponseType = HttpResponse<IEnergyNeedNGRequest[]>;

@Injectable({ providedIn: 'root' })
export class EnergyNeedNGRequestService {
    public resourceUrl = SERVER_API_URL + 'api/energy-need-ng-requests';

    constructor(private http: HttpClient) {}

    create(energyNeedNGRequest: IEnergyNeedNGRequest): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(energyNeedNGRequest);
        return this.http
            .post<IEnergyNeedNGRequest>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(energyNeedNGRequest: IEnergyNeedNGRequest): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(energyNeedNGRequest);
        return this.http
            .put<IEnergyNeedNGRequest>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEnergyNeedNGRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEnergyNeedNGRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(energyNeedNGRequest: IEnergyNeedNGRequest): IEnergyNeedNGRequest {
        const copy: IEnergyNeedNGRequest = Object.assign({}, energyNeedNGRequest, {
            updateDate:
                energyNeedNGRequest.updateDate != null && energyNeedNGRequest.updateDate.isValid()
                    ? energyNeedNGRequest.updateDate.toJSON()
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
            res.body.forEach((energyNeedNGRequest: IEnergyNeedNGRequest) => {
                energyNeedNGRequest.updateDate = energyNeedNGRequest.updateDate != null ? moment(energyNeedNGRequest.updateDate) : null;
            });
        }
        return res;
    }
}
