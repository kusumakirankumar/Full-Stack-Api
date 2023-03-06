import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EmployeeServiceService } from '../service/employee-service.service';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.scss'],
})
export class AddEditComponent implements OnInit {
  formdata: FormGroup;
  constructor(
    private dataform: FormBuilder,
    private service: EmployeeServiceService,
    private dialogRef: MatDialogRef<AddEditComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.formdata = this.dataform.group({
      id:'',
      name: '',
      email: '',
      address: '',
      phone: '',
    });
  }
  ngOnInit(): void {
    this.formdata.patchValue(this.data);
  }

  onSubmit() {
    if (this.formdata.valid) {
      if (this.data) {
        this.service
          .updateEmployee(this.data.id, this.formdata.value)
          .subscribe({
            next: (val:any) => {
              alert('Employee updated successfully!');
              this.dialogRef.close(true);
            },
            error: (err:any) => {
              console.log(err);
            },
          });
      } else {
        this.service.addEmployee(this.formdata.value).subscribe({
          next: (res) => {
            alert('Employee added successfully!');
            this.dialogRef.close(true);
          },
          error: (err) => {
            console.log(err);
          },
        });
      }
    }
  }
}
