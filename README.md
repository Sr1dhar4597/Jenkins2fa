# 2 User Approval in a pipeline

Key Points:
First Approver: The first approval is assigned to USER1.
Second Approver: The second approval is assigned to USER2.

Fail Logic: The pipeline checks if USER2 is the same as the user who approved the first stage (USER1). If they are the same, it fails the deployment.

Testing Steps:

Approval 1: Approve with USER1.
Approval 2: Approve with USER2. The pipeline should proceed to the deployment stage.

Same User Approval: If USER1 tries to approve again in Approval 2, the pipeline should fail with the message: "Deployment Failed: The same user cannot approve both steps!".
