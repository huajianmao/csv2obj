import ClinicMaster from '../examples/data/csvoutpatient/clinicMaster.json';
import OutpMr from '../examples/data/csvoutpatient/outpMr.json';
import OutpOrder from '../examples/data/csvoutpatient/outpOrder.json';
import OutpPresc from '../examples/data/csvoutpatient/outpPresc.json';
import OutpTreatRec from '../examples/data/csvoutpatient/outpTreatRec.json';
import OutpPatMasterIndex from '../examples/data/csvoutpatient/patMasterIndex.json';
import CsvOutpatientLoaderSchema from '../examples/schema/csvoutpatient.json';
import { load } from '../src';
import { FlatSchema } from '../src/types';

describe('Flat loader', () => {
  describe('Functions', () => {
    describe('load', () => {
      it('Identity', () => {
        const schemas: FlatSchema[] = CsvOutpatientLoaderSchema;
        const items = load(schemas, {
          ClinicMaster,
          OutpMr,
          OutpOrder,
          OutpPresc,
          OutpTreatRec,
          OutpPatMasterIndex,
        });
        expect(items?.length).toBe(2);
      });
    });
  });
});
