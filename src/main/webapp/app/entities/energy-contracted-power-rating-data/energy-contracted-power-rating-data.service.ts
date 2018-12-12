import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEnergyContractedPowerRatingData } from 'app/shared/model/energy-contracted-power-rating-data.model';

type EntityResponseType = HttpResponse<IEnergyContractedPowerRatingData>;
type EntityArrayResponseType = HttpResponse<IEnergyContractedPowerRatingData[]>;

@Injectable({ providedIn: 'root' })
export class EnergyContractedPowerRatingDataService {
    public resourceUrl = SERVER_API_URL + 'api/energy-contracted-power-rating-data';

    constructor(private http: HttpClient) {}

    create(energyContractedPowerRatingData: IEnergyContractedPowerRatingData): Observable<EntityResponseType> {
        return this.http.post<IEnergyContractedPowerRatingData>(this.resourceUrl, energyContractedPowerRatingData, { observe: 'response' });
    }

    update(energyContractedPowerRatingData: IEnergyContractedPowerRatingData): Observable<EntityResponseType> {
        return this.http.put<IEnergyContractedPowerRatingData>(this.resourceUrl, energyContractedPowerRatingData, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IEnergyContractedPowerRatingData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IEnergyContractedPowerRatingData[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
